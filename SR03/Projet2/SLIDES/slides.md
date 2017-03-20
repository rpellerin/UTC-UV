% Projet QCM - Java EE 
% Steve Lagache, Romain Pellerin
% SR03 27/05/2016

-------------------------------------------

# Cahier des charges

- Java EE 
- Tomcat
- MySQL

-------------------------------------------

# Cahier des charges

- Gestion :
    - d'utilisateurs (admin et stagiaire)
    - de QCM (que nous avons appelés quiz)
    - de questions
    - de réponses
    - des parcours d'utilisateurs
- Pouvoir passer un QCM, visualiser ses scores

-------------------------------------------

# Technos

- Java EE 8 (nouvelles fonctionnalités)
- Tomcat 8 (*latest stable*)
- Pas de bibliothèque autre que JSTL et MySQL connector

-------------------------------------------

# *Design patterns*

- DAO
- MVC
- Héritage
- Singleton (DB)

-------------------------------------------

<img src="assets/db.png" alt="BDD" class="w30" style="margin-top: -75px"/>

-------------------------------------------

# Architecture \#1

<img src="assets/src_archi.png" alt="Architecture" class="w50" />

-------------------------------------------

# Architecture \#2

<img src="assets/web_archi.png" alt="Architecture" class="w30" />

-------------------------------------------

# Fonctionnement

<img src="assets/arch.png" alt="Architecture" class="w90" />

-------------------------------------------

# À savoir 

- Filtres (2)
- Base de données
    - Cohérence
    - Contrôles
    - Pas de redondance
- Beans
    - Calqués sur la BDD
- DAO
    - Héritage
    - Interface (abstract)

-------------------------------------------

# À savoir 

- Email
    - Javamail
- Gestion projet
    - Eclipse
    - CLI (MySQL & Git)

-------------------------------------------

# Conclusion

- Sur le projet

- Sur Java EE

-------------------------------------------

# DEMO 

<br />
<br />
[http://localhost:8080/Projet2/](http://localhost:8080/Projet2/)

-------------------------------------------

Merci

_

Q & A
