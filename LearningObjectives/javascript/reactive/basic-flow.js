import { of } from 'rxjs';

// Observable: Emits the data
const database = of("1", "2", "3", "4");

// Observer: Listens to emissions from the Observable
const observer = {
  next: (value) => console.log(`Received: ${value}`),
  error: (err) => console.error(`Error: ${err.message}`),
  complete: () => console.log('All data received.')
};

// Subscribe the observer to the observable
database
  .subscribe(observer);
