% Facts
parent(john, mary).
parent(mary, tom).
parent(mary, lisa).
parent(jane, mary).
parent(jane, alex).

% Rule for defining grandparent
grandparent(X, Y) :- parent(X, Z), parent(Z, Y).

% Rule for defining sibling
sibling(X, Y) :- parent(P, X), parent(P, Y), X \= Y.

% Rule for defining uncle
uncle(X, Y) :- sibling(X, P), parent(P, Y).



% How to run:
% Start SWI-Prolog from terminal: swipl
% Load the Prolog file: consult('family-tree.pl').

% Run some queries
% ?- grandparent(john, tom).
% ?- sibling(mary, alex).
% ?- uncle(alex, tom).