# Projet JPA

Création de tables livres, clients, commandes... Et exploitation des ces tables à travers la JPA

## Menu de la banque
```

***** Bienvenue dans le logiciel de gestion des banques, des clients, et toute la finance de tout le mond entier *****
"Menu principal. Veuillez faire votre choix"
1. Afficher la liste des clients
2. Ajouter un nouveau client
3. Ajouter un compte à des clients existants
4. Effectuer une opération sur un compte existant
99. Sortir
```


## Instructions possibles
Les opérations suivantes ont été rendues possibles :
### TP 3
Le TP 3 demandait les instructions suivantes :
>Créer les entités JPA suivantes et mettez en place les annotations relationnelles :
>
>o Banque
>
>o Compte
>
>o Operation
>
>o Client
>	
>	▪ Adresse (Embeddable)
>
>
>- Les tables seront générées à partir des relations définies dans votre modèle métier
>- Insérer en base de données des instances des différents objets en utilisant l’EntityManager

### TP 4
Le TP 4 demandait les instructions suivantes :
>Créer les entités JPA suivantes et mettez en place les annotations d’héritage :
>
>o LivretA
>
>o AssuranceVie
>
>o Virement
>
>
>- Insérer en base de données des instances des différents objets en utilisant l’EntityManager
>- Insérer un compte associé à 2 clients
>- Insérer un client avec plusieurs comptes :
>	
	>▪ 1 compte de type assurance vie
>
	>▪ 1 compte de type livret A
>- Insérer des opérations de type virements sur un compte
>- Insérer des opérations de type opérations sur un compte