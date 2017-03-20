DROP TABLE IF EXISTS PROJETS;
DROP TABLE IF EXISTS TACHES;
DROP TABLE IF EXISTS TACHES_PRECEDENTES;
DROP TABLE IF EXISTS EVENEMENTS;

CREATE TABLE PROJETS (
  nom TEXT PRIMARY KEY NOT NULL,
  date_disponibilite DATE -- Peut être NULL
);

CREATE TABLE TACHES (
  tache_id INTEGER PRIMARY KEY,
  nom TEXT NOT NULL,
  projet_conteneur TEXT REFERENCES PROJETS, -- le projet qui contient cette tache, peut etre NULL si contenue dans une tache
  tache_conteneur INTEGER REFERENCES TACHES, -- la tache qui contient cette tache, peut etre NULL si contenue dans un projet directement
  date_disponibilite DATE, -- Peut être NULL
  is_composite BOOLEAN NOT NULL,
  is_preemptable BOOLEAN, -- Peut être NULL si la tâche est composite
  duree INTEGER, -- Peut être NULL si la tâche est composite
  etat TEXT check (etat in ('NON_COMMENCEE', 'COMMENCEE', 'INTERROMPUE', 'REPRISE', 'TERMINEE')), -- Peut etre NULL si composite
  echeance DATE, -- Peut être NULL
  UNIQUE (nom, projet_conteneur),
  UNIQUE (nom, tache_conteneur),
  CHECK (
    (is_composite = 'f' and is_preemptable IS NOT NULL and duree IS NOT NULL and etat IS NOT NULL)
    or
    (is_composite = 't' and is_preemptable IS NULL)
  ),
  CHECK (
    (projet_conteneur IS NOT NULL and tache_conteneur IS NULL)
    or
    (projet_conteneur IS NULL and tache_conteneur IS NOT NULL)
  )
);

CREATE TABLE TACHES_PRECEDENTES (
  tache_dependante INTEGER NOT NULL REFERENCES TACHES,
  tache_precedente INTEGER NOT NULL REFERENCES TACHES,
  PRIMARY KEY (tache_dependante,tache_precedente)
);

CREATE TABLE EVENEMENTS (
  evenement_id INTEGER PRIMARY KEY,
  titre TEXT NOT NULL check (titre <> ""),
  description TEXT, -- Peut être NULL
  lieu TEXT, -- Peut être NULL
  debut DATETIME NOT NULL,
  fin DATETIME NOT NULL,
  tache_ref TEXT REFERENCES TACHES,
  check (debut <= fin),
  UNIQUE (titre, description, lieu, debut, fin)
);