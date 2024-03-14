# Nommer les branches

## Les types de branches

Le type d’une branche doit être clair afin de comprendre le but de celle-ci. Voici une liste non exhaustive des types de branches :

- **feature**: Ajout d’une nouvelle fonctionnalité
- **bugfix**: Correction d’un bug
- **hotfix**: Correction d’un bug critique
- **chore**: Nettoyage du code
- **experiment**: Expérimentation de fonctionnalités

## Le nom de la branche

Le nom de la branche décrit le but de celle-ci. Certaines règles doivent être respectées :

- Le nom doit faire moins de 50 caractères
- Le nom doit respecter la convention kebab-case

## Quelques exemples

- feature/add-users-controller
- hotfix/profile-page-error/621
- experiment/try-api-key
- chore/remove-deprecated-method/924


# Nommer les messages de commits

## Le type

Le type du commit décrit l’origine du changement. Il peut prendre différentes valeurs :

- **feat**: Ajout d’une nouvelle fonctionnalité;
- **fix**: Correction d’un bug;
- **build**: Changement lié au système de build ou qui concerne les dépendances (npm, grunt, gulp, webpack, etc.).
- **ci**: Changement concernant le système d’intégration et de déploiement continu (Jenkins, Travis, Ansible, gitlabCI, etc.)
- **docs**: Ajout ou modification de documentation (README, JSdoc, etc.);
- **perf**: Amélioration des performances;
- **refactor**: Modification n’ajoutant pas de fonctionnalités ni de correction de bug (renommage d’une variable, suppression de code redondant, simplification du code, etc.);
- **style**: Changement lié au style du code (indentation, point virgule, etc.);
- **test**: Ajout ou modification de tests;
- **revert**: Annulation d’un précédent commit;
- **chore**: Toute autre modification (mise à jour de version par exemple).

Pour chacun des types, vous pouvez également utiliser un système d’emoji comme [gitmoji](https://gitmoji.carloscuesta.me/).

## Le scope

Cet élément facultatif indique simplement le contexte du commit. Il s’agit des composants de notre projet, voici une liste non exhaustive :

- controller;
- route;
- middleware;
- view;
- config;
- service;
- etc.

## Le sujet

Le sujet décrit succinctement la modification. Certaines règles doivent être respectées :

- Le sujet doit faire moins de 50 caractères;
- Les verbes doivent être à l’impératif (add, update, change, remove, etc.);
- La première lettre ne doit pas être en majuscule;
- Le sujet ne doit pas se terminer par un point.

## Quelques exemples

**Commit simple (sans corps ni footer)**

- feat(controller): add post's controller
- docs(route): add swagger documentation
