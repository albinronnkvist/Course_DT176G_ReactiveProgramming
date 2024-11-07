% Facts
parent(john, mary).
parent(john, alex).
parent(mary, tom).
parent(mary, albin).
parent(alex, lisa).
parent(alex, tom).

% Rule for siblings
sibling(X, Y) :- parent(P, X), parent(P, Y), X \= Y.

% How to run:
% Start SWI-Prolog from terminal: swipl
% Load the Prolog file: consult('backtracking.pl').

% Query:
% ?- sibling(tom, Sibling).
% Prompt to continue: ";"

% Result:
% Sibling = albin ;
% Sibling = lisa ;
% false.
