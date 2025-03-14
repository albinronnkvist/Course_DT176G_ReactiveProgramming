# Instructions

## Overview

This chatbot provides contextual responses based on user input messages. It operates within predefined contexts to determine relevant replies and suggestions for further user input. 

In short, the user message is mapped to a bot response and a context, but only the response is immediately displayed. The `scan` operator accumulates the context, applying it in the next exchange so that each response is based on the previously mapped context.

### Contexts

The chatbot operates within the following contexts and sub-contexts, with supported user input message mappings for each:

- `DEFAULT` (help, hi, hello, hey)
- `SHIPPING` (shipping, delivery)
    - `TRACKING` (tracking, track)
    - `DELAY` (delay, delayed, late)
    - `COST` (cost, price)
- `PAYMENT` (payment)
    - `REFUND` (refund)

### User input messages

There are two alternatives for the user to send messages:

- **Suggestion buttons**: Click on a suggestion button, which is always a valid input in the current context.
- **Text input field**: Enter a supported message (see _Contexts_ section above) in the text input field and press the **Send** button.

**Unsupported message**: If a user enters a message that doesn't match any recognized suggestions or synonyms within the current context, the chatbot will respond with:  
❝ *I didn't understand that. Can you provide more details?* ❞  
When this happens, the conversation will still remain in the same context, allowing a retry with a supported message.

### Reset

At any time, the user can press the **Reset** button to reset the conversation, clearing the messages and returning to the `DEFAULT` context.

---

## Step-by-Step Guide

### 1. Starting the Application
- **Context**: `DEFAULT`
- **Bot Message**: "Hello! How can I help you?"
- **User Actions**:
    - Click one of the suggestion buttons: `"help"`, `"shipping"`, or `"payment"`
    - Enter an alternative synonym in the text input field and press **Send**
        - `"help"` → _(Synonyms: "hi", "hello", "hey")_
        - `"shipping"` → _(Synonym: "delivery")_
        - `"payment"`

### 2. User Selects `"shipping"`
- **Current Context**: `DEFAULT`
- **Next Context**: `SHIPPING`
- **Bot Message**: "Sure, I can help with shipping. Can you provide more details?"
- **User Actions**:
    - Click one of the suggestion buttons: `"tracking"`, `"delay"`, or `"cost"`
    - Enter an alternative synonym in the text input field and press **Send**
        - `"tracking"` → _(Synonym: "track")_
        - `"delay"` → _(Synonyms: "delayed", "late")_
        - `"cost"` → _(Synonym: "price")_

### 3. User Selects `"tracking"`
- **Current Context**: `SHIPPING`
- **Next Context**: `SHIPPING` _(sidenote: could have been `TRACKING`)_
- **Bot Message**: "Sure, I can help with tracking your order. Can you provide your order number?"
- **User Actions**:
  - Enter an order number in the text input field and press **Send**
      - **Correct format**: An `8`-digit number (e.g., `"12345678"`)
      - **Incorrect format**: Any input that is not an `8`-digit number  
      _(Triggers an error message prompting for a retry with the correct format)_

### 4. User Enters a Valid Order Number
- **Current Context**: `SHIPPING` _(sidenote: could have been `TRACKING`)_
- **Next Context**: `DEFAULT`
- **Bot Message**:  
  "Thank you for providing your order number. Your order is currently at our warehouse in Stockholm and is estimated to arrive in 1-2 business days."
- **User Actions**: Same as in Step 1.
- **Other Actions**: The conversation context resets to the initial `DEFAULT` context.

---

## Example

Example conversation from start to finish, showing in what context the bot responds.

1. **User opens the chatbot and wants help with shipping**  
    - 🤖 (DEFAULT context) `"Hello! How can I help you?"`
    - ⬇️ **User selects** `"shipping"`

2. **User wants help with tracking**  
    - 🤖 (DEFAULT context) `"Sure, I can help with shipping. Can you provide more details?"`  
    - ⬇️ **User selects** `"tracking"`

3. **User provides an order number**  
    - 🤖 (SHIPPING context) `"Sure, I can help with tracking your order. Can you provide your order number?"`  
    - ⬇️ **User enters** `"12345678"`

4. **Chatbot provides tracking details & resets the context**  
    - 🤖 (SHIPPING context _(sidenote: could have been TRACKING context)_) `"Thank you for providing your order number. Your order is currently at our warehouse in Stockholm and is estimated to arrive in 1-2 business days."`

---

## Side Note (context improvements)

I realized that I never actually apply the sub-contexts; instead, I remain in the main contexts while only utilizing the word mappings from the sub-contexts. I could have taken this further by explicitly tracking sub-contexts as well.  

For instance, in **Step 4** of the example, the response could have been within the `TRACKING` sub-context rather than staying in the `SHIPPING` context. I’ve also highlighted this potential improvement in **Steps 3 and 4** of the Step-by-Step Guide.  

Currently, I only have `DefaultResponder`, `ShippingResponder`, and `PaymentResponder` (along with suggestors). However, I could extend this by creating dedicated responders for sub-contexts, such as a `TrackingResponder` for the `TRACKING` sub-context.