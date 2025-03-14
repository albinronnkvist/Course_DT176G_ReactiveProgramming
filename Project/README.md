# Final Project

## Environment & Tools

This project was developed on a VivoBook ASUS Laptop (X421IA_M433IA, 8GB RAM) running Ubuntu 24.04.1. The development environment included VSCodium v1.95.3 as the code editor and Git v2.43.0 for version control. The codebase was written in Java (OpenJDK) v21.0.5 and built using Apache Maven v3.8.7.

## Purpose

The purpose of this project is to develop a Java-based chatbot that applies principles drawn from the functional and reactive programming paradigms. It places emphasis on constructing streamed pipelines, using the __Streams API__ for the functional aspect and __RxJava__ for the reactive aspect.

The primary objective is to build a lightweight, rule-based chatbot that can map user inputs to suitable responses. Additionally, it should handle more complex conversations by keeping track of the conversation context. Accomplishing this, while adhering to the earlier mentioned principles, involves meeting the following goals:

- __Reactive Concepts (RxJava):__
    - __Observable & Observer:__ Implement the Observable-Observer pattern, with RxJava's `Observables` and `Observers`, to manage the flow of user input and chatbot responses.
    - __Operators:__ Use and chain core RxJava operators to process and transform data streams, such as user inputs.
    - __Combining Observables:__ Use combining operators, like `merge` and `zip`, to coordinate multiple streams and show how it can create cohesive responses.
    - __Multicasting:__ Distribute a single data source across multiple observers, using `publish()` or `replay()`. Also use subjects, such as `PublishSubject`, to dynamically emit or relay events.
    - __Concurrency & Parallelization:__ Use appropriate RxJava schedulers, such as `computation()`, to keep operations non-blocking and efficient.
    - __Buffering / Throttling / Switching__: Use buffering to batch rapid input/output into manageable groups. Prevent excessive/redundant inputs by applying throttling. Use `switchMap()` to dynamically switch between streams.
    - __Flowable & Backpressure:__ Handle high-volume inputs/outputs with RxJava's `Flowable` and backpressure strategies such as buffering or dropping.
- __Functional Concepts:__
    - Ensure immutability and stateless design, avoiding side-effects.
    - Use features from the _Streams API_.
    - Manage conversation states using recursion or functional / reactive streams.
- __Error Handling:__
    - Gracefully manage errors, using operators such as `onErrorResumeNext` and `retry`
    - Provide alternate responses for unrecognized or problematic inputs.
- __Usability:__
    - Offer a simple, clear user interface.
    - Include clear usage instructions and guidance.
    - Ensure fast and readable responses.

To ensure high-quality implementation and adherence to best practices, the following technical guidelines will be followed:

- __Java Version:__ Use JDK 21.
- __Build and Dependency Management:__ 
    - Follow the predefined Maven project structure.
    - Use only dependencies which are available in Maven Central and support JDK 21.
    - Adhere to the predefined file structure and logically separate files into appropriate sub-packages for modularity. Include `package-info.java` descriptions to document the purpose and functionality of each package.
    - Ensure every entity is documented with JavaDocs. Include main descriptions (class, interface, record, etc.), author tags, and method specifications for shared interfaces (public/protected).
- __Executable Artifact:__ 
    - Ensure the project generates a standalone executable JAR file using the command mvn clean verify.
    - Confirm the JAR can run outside the development environment as a fully operational application.

Finally, the report must reside in this `README.md` and follow the writing compendium requirements:

- __Purpose:__ Align project goals with requirements.
- __Methodology:__ Detail the implementation process.
- __Discussion:__ Analyze results, validate success, and explore alternative approaches. Maintain objectivity.
- __Personal Reflections:__ Include subjective views in this specific section.

## Procedures

The first step in creating the chatbot application was to establish a clear separation between the user interface (UI) and the core business logic. Initially, the Model-View-Controller (MVC) pattern was chosen due to previous experience with it. This decision allowed early prototyping to focus on laying out the basic UI without considering any underlying functionality. The UI initially included a thread view, responsible for displaying the entire message history, and a "send message" view, providing input fields and buttons for sending user messages.

After setting up the basic UI, the next step involved wiring it to a reactive event-driven data flow. An early assumption was made that several parts of the application would be interested in emitting and subscribing to both user messages and chatbot responses. Therefore, a decision was made to group this logic in a central service for reusability, this service is now called `ChatService`. To be able to both emit user messages and observe on bot responses, `Subjects` were deemed appropriate, since they serve a dual role as both Observers (capable of subscribing to other Observables) and Observables (capable of emitting data to its subscribers). This approach created a bridge between the imperative Swing UI and reactive/functional business logic. Rather than exposing reactive `Subjects` directly from the `ChatService`, methods were introduced to safely handle user input events and chatbot response events. This was done by providing wrapper functions that safely invoke `onNext()` and expose `Observables` for subscription. At first, an attempt was made to handle user messages and chatbot messages in the same data stream, but this approach proved cumbersome: Observers interested only in user inputs had to filter out bot messages, and vice versa. To resolve this, separate streams were established to keep each type of message isolated, thus reducing unnecessary processing in observers.

As soon as basic message passing was confirmed to work, efforts turned toward implementing a chatbot responder. Initially, it was a simple non-contextual responder that used regular expressions to match certain keywords in user messages. This was achieved with the `Streams API`, allowing filtering, mapping, and fallback response selection in a compact and stateless pipeline. The regex-building logic was later extracted into a `RegexPatternBuilder` utility class where the logic was broken down into smaller testable functions. 

A more advanced requirement was to retain a sense of context throughout the conversation. To accomplish this, enums were defined for each context and sub-context, each holding an immutable list of associated keywords. Corresponding `Responder` classes were created, one for each context type, responsible for responding to user input using the previously mentioned regular expression matching. However, these classes produce a more advanced immutable `ContextualResponse` record, containing the context of the response in addition to the text. Now, instead of simply mapping a keyword to a response, the `ChatModel` maps the context from the previous `ContextualResponse` to decide which `Responder` is appropriate to respond in the current context. 
```
public static ContextualResponse getContextualBotResponse(ContextualResponse previousContextualResponse, Message userMessage) {        
    return responders.getOrDefault(
            previousContextualResponse.context(), 
            message -> DefaultResponder.getContextualResponse(message)
        )
        .apply(userMessage.text());
}

private static final Map<Context, Function<String, ContextualResponse>> responders = Map.of(
        Context.DEFAULT, message -> DefaultResponder.getContextualResponse(message),
        Context.SHIPPING, message -> ShippingResponder.getContextualResponse(message),
        ...
```

For a moment this approach moved away from stateless design by making the `ChatModel` handle context states. To prevent this, the context state management was moved to the `ChatViewModel`, where the `scan()` operator was used to keep track of the context in the reactive chain. `scan()` accumulates the previous `ContextualResponse` and the new user input, producing the next `ContextualResponse` based on the appropriate `Responder`. Initially, it was overlooked that `scan()` yields a default item at subscription, so `skip(1)` was added to ignore that extra emission.
```
chatService.getUserMessageStream()
    .scan(
        ChatModel.getInitialContextualResponse(),
        (contextualResponse, userMessage) -> ChatModel.getContextualBotResponse(contextualResponse, userMessage)
    )
    .skip(1)
)
.publish();
```

Once the context-based responses were established, the next challenge was displaying user messages and chatbot replies side by side in a cohesive manner. This was accomplished using the `zip()` operator, which synchronizes emissions from multiple streams. In this setup, one stream emits user messages while another emits the corresponding chatbot responses. The `zip()` operator waits for each stream to produce an item, then pairs them together into a single emission packaged into an immutable `MessagePair` record, ensuring that a given user message is always displayed alongside its matching bot reply.

With a contextual conversation in place and displayed in the UI, the ability to reset the conversation became a priority. In the initial approach, a separate `ResetService` wrapped the `ChatService` and exposed the same Observables through its own methods. The idea was to emit a “reset” event from the `ResetService` and then use `switchMap()` to replace the Observables exposed by the `ResetService` methods. 
```
// ResetService

private final BehaviorSubject<ChatService> resetSubject = BehaviorSubject.createDefault(new ChatService());

public Observable<Message> getBotMessageStream() {
    return resetSubject.switchMap(ChatService::getBotMessageStream);
}
```

However, this approach introduced too much complexity and state management. To simplify, the conversation-reset logic was consolidated into a single `ChatService`. In other words, the ChatService was extended with a new `BehaviorSubject` for relaying reset events. 
The reset stream was multicasted with `.replay(1).refCount()`, ensuring that subscribers shared the same reset stream and that late subscribers received the latest cached data when there were other active subscribers. Initially, the idea was to use the `Void` type for the reset emissions, to simply signal that something has happened. However, that would have required passing `null` in `onNext()`, which is not allowed in _RxJava 3.x_ due to the mandatory compatibility with the _Reactive Streams_ specification. To solve the issue, a `Boolean` type with a constant value of true was initially used, to later be replaced with a more appropriate `ContextualResponseWithSuggestions` record used to provide default values to subscribers on reset.
This reset event was initially meant for restarting the `scan()` operator to reset the conversation context. Upon a reset emission from `getResetStream()`, the `switchMap()` operator switches to a new `getUserMessageStream()` Observable and effectively reinitializes the `scan()` operator with its initial state.
```
chatService.getResetStream()
    .switchMap(__ -> chatService.getUserMessageStream()
        .scan(
            ChatModel.getInitialContextualResponse(),
            (contextualResponse, userMessage) -> ChatModel.getContextualBotResponse(contextualResponse, userMessage)
        )
        .skip(1)
    )
    .publish();
```

After adding the reset behavior, the majority of basic functionality had been implemented. As the codebase had grown, it became apparent that the MVC pattern had led to controllers handling and mixing a significant amount of logic, necessitating several public methods to access components from the Views. To reduce this burden and draw clearer boundaries, the design was refactored to Model-View-ViewModel (MVVM). In MVVM, the Model is accessible only to the ViewModel, and the ViewModel is accessible only to the View, establishing a more straightforward pipeline of interactions. This made it easier to maintain the code and introduced the possibility to modify or replace the UI without affecting the rest of the codebase.

With a cleaner project structure and the basic functionality in place, the focus shifted on making the application more efficient and robust. The initial step was to ensure responsiveness by managing threads. A `SwingScheduler` utility class was created, which provides a custom RxJava `Scheduler` that ensures actions are executed on the Swing event dispatch thread. By inserting the `observeOn(SwingScheduler.GET)` operator before subscribing in the Views, downstream UI operations were guaranteed to run on the correct thread. For computational tasks, RxJava’s `computation()` scheduler was used. An early assumption was that `subscribeOn(computation())` in the upstream Observables would automatically shift all operations to the computation thread, but it was discovered that any emission triggered from another thread would continue there unless interrupted by an `observeOn()` operator. Placing `observeOn(computation())` early in the chain addressed this issue, preventing CPU-bound work from blocking other threads.

Before continuing with improving efficiency and robustness, a new feature was introduced to guide users with input suggestions for the current context. `Suggester` classes were created in a manner similar to the `Responder` classes, subscribing to the same contextual stream and determining which keywords best represent the active context or sub-context. These keywords were then displayed as clickable buttons that, when pressed, emitted user messages containing the predefined keywords. Because suggestions and bot response subscribers relied on the context coming from the same upstream `contextualChat` Observable in the `ChatViewModel`, it was multicasted with the `publish()` operator to create a `ConnectableObservable`, allowing the subscribers to share the same stream and avoiding duplicate upstream processing. Lastly, the `connect()` operator was used to explicitly signal the now multicasted `contextualChat` to start emitting items to its subscribers.
```
var contextualChat = ...
    .publish();

contextualChat
    ...
    .subscribe(
        chatService::sendBotMessage, ...);

contextualChat
    ...
    .subscribe(
        chatService::sendMessageSuggestions, ...);

contextualChat.connect();
```

To handle potential high-volume usage, the main streams in the `ChatService` were changed from `Subject`/`Observable` to `Processor`/`Flowable` to enable backpressure handling. Certain streams, such as the reset and suggestion streams, used `onBackpressureLatest()` to keep only the newest item when the stream was saturated, avoiding accumulation of stale data. For user-message and bot-message streams, `onBackpressureBuffer()` was selected so messages would be queued rather than dropped. Since it's difficult to backpressure user-generated events like button clicks, throttling was applied to further prevent users from sending an excessive amount of messages while also preventing potential redundant rapid clicks. To achieve this, the `throttleFirst()` operator was used to only include the first message sent within the specified time window, while dropping the rest.

Continuing on improving robustness, a combination of railway-oriented and reactive error handling was implemented. It relied on an immutable `Result` type to represent either a success track or a failure track. Methods such as `isSuccess()` and `isFailure()` identified the state of the `Result`, while downstream logic accessed the success value using `value()` or the error using `error()`, making error handling explicit and declarative. The `onErrorReturnItem()` was used to emit a default item in case of an error in the reactive chain, allowing downstream subscribers to process the error as an `onNext()` event. Errors were communicated to the user via the `notificationStream`, where a dialog was displayed, prompting the user to reset the stream. Additionally, buffering was applied to the `notificationStream` with the `buffer()` operator, to group notifications within the specified timeframe, which allowed filtering duplicates and grouping error messages in downstream subscribers. Upstream observables were dynamically replaced after a reset by applying `switchMap()` to the reset stream, ensuring the user can restart from a clean slate.
Finally, a simple retry mechanism with the `retry()` operator was added in the message suggestions subscriber in case an error was encountered in the pipeline, such as if `getMessageSuggestions` would fail. The `retry(3)` operator instructs the reactive pipeline to re-subscribe to the upstream source when an error occurs. It will retry the sequence up to 3 times before finally propagating the error downstream to the `onError` consumer in the subscribe block.

In conclusion, the chatbot development journey involved extensive trial and error, starting with establishing a clear separation between the UI and core business logic, evolving through multiple iterations, including transitioning from MVC to MVVM for better maintainability, and incorporating reactive and functional programming principles to handle contextual user-chatbot interactions with streamed pipelines. All resulting in a robust, context-aware chatbot.

## Discussion
### Purpose Fulfillment

The purpose of this project was to develop a Java-based chatbot applying principles from functional and reactive programming. A lightweight rule-based chatbot was created to map user inputs to appropriate responses, while managing conversation context for more complex interactions. The implementation successfully demonstrated the use of streamed pipelines with the __Streams API__ for functional programming and __RxJava__ for reactive programming. The project met the following goals:

- __Reactive Concepts (RxJava):__  
     - __Observable & Observer:__ The Observable-Observer pattern was used to handle the flow of user inputs and chatbot responses. The `getUserMessageStream()` method in the `ChatService` exposes an Observable which emits user inputs. In the `ChatViewModel`, the chatbot model is set up to observe the user inputs and trigger a response.
     - __Operators:__ Core RxJava operators were chained to process and transform data in various scenarios. For example, `filter()` and `map()` were used to validate and map user input in `getUserMessageStream()`.
     - __Combining Observables:__ Multiple streams were combined with RxJava operators to create cohesive responses. For example, `getMessagePairStream()` utilized `zip()` to combine each user input message with a chatbot response. Additionally, `merge()` was used in `getMessageSuggestionsStream()` to combine the initial message suggestions originating from a reset, with message suggestions originating from the latest chatbot response.
     - __Multicasting__ was demonstrated in multiple places. The `resetStream` uses `replay(1)`  to multicast its last value to all subscribers and the `contextualChat` stream in the `ChatViewModel` uses `publish()` to reuse the process of generating contextual chatbot responses across multiple subscribers. Additionally, the `PublishProcessor` instances (e.g. `userMessageProcessor`) act as subjects to dynamically emit or relay events. For example, `userMessageProcessor` dynamically emits user messages to the `getUserMessageStream()` via the exposed `sendUserMessage()` method.
     - __Concurrency & Parallelization:__ The `computation()` scheduler was utilized to enable non-blocking and efficient processing of inputs and responses. Additionally, the `SwingScheduler` ensured that UI events were executed on the Swing thread.
     - __Buffering / Throttling / Switching__: Buffering was applied to the `notificationStream` with the `buffer()` operator, to batch notifications within a brief time window. This allows downstream subscribers to manage potentially large amount of notifications as a group, and for example filter out excessive duplicates for better performance. Throttling was applied with `throttleFirst()` to limit excessive user input in the `userMessageStream`, taking only the first input within the time window and dropping the rest. Switching was applied with `switchMap()` to reset the chat context in the `contextualChat` stream.
     - __Flowable & Backpressure:__ High-volume inputs were handled gracefully with `Flowable` and backpressure strategies. For example `.onBackpressureLatest()` was used on the `resetStream`, since only the latest value is interesting. Additionally, `onBackpressureBuffer()` was for example used on the `userMessageStream`, to allow all messages to be emitted as fast as possible under heavy load.
- __Functional Concepts:__  
     - Immutability and stateless design principles were largely followed, minimizing side effects wherever possible. For example, avoiding mutable variables by using streams and function composition, using `List.of()` and `Map.ofEntries()` to create immutable lists and maps, using `records` over `classes` where possible to prevent fields from being reassigned. However, the UI implemented with Java Swing is inherently imperative. Consequently, some components used to bridge the imperative UI with the reactive/functional business logic, such as the mutable processors in the `ChatService`, relied on stateful design.
     - The __Streams API__ was heavily utilized for data processing, such as in the `ChatModel`'s `responder` and `suggester` helper classes. Conversation states/context were managed using reactive streams, with the `scan()` operator handling state transitions without mutable variables. 
- __Error Handling:__  
     - Errors were managed gracefully using operators such as `onErrorReturnItem()` and `retry()`. For example, `userMessageStream` returns a `Result.failure()` item from an `onErrorReturnItem()` operator, allowing downstream subscribers to handle it gracefully as an `onNext` event. The `.retry(3)` operator was applied in the message suggestions subscriber, to attempt a resubscription up to three times in case of an error in the upstream chain.
     - Alternate responses were provided for unrecognized or problematic inputs, for example `orElse(getFallback())` in the responders' `getContextualResponse()` methods.
- __Usability:__  
     - The chatbot offers a simple interface where the user can see the full conversation and send messages via manual inputs. The user can also simply click one of the suggestions, which are dynamically generated after each response, and serves as additional guidance. Finally, the user is notified about errors and can recover from it.

Technical guidelines were followed throughout the project:  

- __Java Version:__ JDK 21 was used.  
- __Build and Dependency Management:__  
     - The predefined Maven project structure was used. 
     - Only dependencies available in the Maven Central and with support of JDK 21 were used.
     - Logical sub-packages with `package-info.java` files were created for clarity.  
     - JavaDocs were written for all entities, with descriptions, author tags, and method specifications.  
- __Executable Artifact:__  
     - A standalone executable JAR file was successfully generated using `mvn clean verify`.  
     - The JAR ran smoothly outside the development environment as a fully operational application.  

The accompanying report in `README.md` followed the writing compendium requirements, including purpose, methodology, discussion, and personal reflections. Each section was structured to validate the success of the project and explore alternative approaches, ensuring an objective and comprehensive analysis.

Overall, the project successfully demonstrated the application of functional and reactive programming paradigms in building a robust chatbot, achieving the outlined objectives.

### Alternative Approaches

In the current implementation, the majority of streams in the `ChatService` are multicast to allow multiple subscribers to reuse the same upstream source. However, this was not strictly necessary for all of them, since some are used by only one subscriber. Instead of multicasting, another approach could have been to directly return the unshared `Flowable`, reducing the overhead introduced by multicasting and simplifying the overall design. Despite the current lack of multiple subscribers, multicasting was implemented to maintain a consistent design across streams.

A consistent design for error handling was also attempted, where errors were emitted as `Result.failure(...)` types in `onNext` events. An alternative would have been to let RxJava manage errors via `onError` events. This approach is more straightforward, allowing the observable chain to throw terminal errors, which subscribers can handle using `onError` callbacks. However, it lacks flexibility in processing errors further down the chain, such as combining multiple errors shown in `getMessagePairStream`:
```
Flowable.zip(
    getUserMessageStream(),
    getBotMessageStream(),
    (userResult, botResult) -> Stream.of(userResult, botResult)
        .filter(Result::isFailure)
        .map(Result::error)
        .map(ErrorDetail::message)
        .reduce((msg1, msg2) -> msg1 + "; " + msg2)
        .map(errorMessage -> Result.<MessagePair, ErrorDetail>failure(new ErrorDetail(errorMessage)))
        .orElseGet(() -> Result.<MessagePair, ErrorDetail>success(
            new MessagePair(userResult.value(), botResult.value())
        )
))
```
While providing more flexibility, it also introduced complexity in managing `Result` types, where passing `onError` events would have been simpler.

Another alternative in the error handling would have been to place the `retry()` operator after the hot streams, acting as a safeguard against stream termination. Instead of requiring the user to manually reset on each error, `retry()` could have automatically resubscribed to the source when it encountered an `onError` and have kept the reactive flow “alive”. The trade-off is that, if errors are persistent or if there is no sensible recovery path, `retry()` can lead to repeated failures or infinite loops. In such cases, adding conditions (e.g., limiting the number of attempts) or using `retryWhen()` with back-off delays could have been appropriate.

On top of error handling, handling heavy load could have been implemented differently. The backpressure buffer strategy could have been more efficient in the `userMessageStream`, such as only buffering a certain amount of items and dropping older ones:
```
.onBackpressureBuffer(100, null, BackpressureOverflowStrategy.DROP_OLDEST);
``` 
Another solution could have been to block the user from sending a new message until a chatbot response is provided for the previous message. This blocking could have been achieved with the help of the zipped `getMessagePairStream()` stream, which waits with emissions until both parties have produced a message. Additionally, this could have been displayed with a loading icon or typing indicator, to make the UI more responsive.

Although not specific to reactive or functional programming, the MVVM pattern could have separated concerns further. Instead of exposing models like `Message` and `ErrorDetail` directly to the Views, these could have been mapped to DTOs, with the Views interacting only with the DTOs for better encapsulation and maintainability.

To conclude, the proposed approaches each have their merits, and the choice between them ultimately depends on weighing simplicity against flexibility and control, as well as overall design preferences.

## Personal Reflections

I have gained a lot of insight into reactive programming during this project, but also many questions. It was extremely challenging to reason about error handling and how to handle it gracefully while keeping hot streams active or being able to reset them. Another challenge was the separation of concerns and composing the reactive streams. It took some iterations to come up with a manageable structure.

The chatbot could have been more complex and had more features. It could also have had a cleaner and more responsive UI, however I got stuck on more concerning issues, such as error handling.

The commit history got messy at the end, due to a lot of trial and error. I should have experimented more on separate branches and had better test coverage.

Overall, it was a challenging project, forcing me to find alternatives to my otherwise imperative mindset. 