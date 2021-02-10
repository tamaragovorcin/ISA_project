ISA

[![Build Status](https://travis-ci.com/tamaragovorcin/ISA_project.svg?branch=develop)](https://travis-ci.com/tamaragovorcin/ISA_project)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=tamaragovorcin_ISA_project&metric=alert_status)](https://sonarcloud.io/dashboard?id=tamaragovorcin_ISA_project)


Fakultet tehnickih nauka Novi Sad
Primenjene racunarske nauke
Projekat iz predmeta Internet softverske arhitekture

Travis: https://travis-ci.com/github/tamaragovorcin/ISA_project

Autori:
  Luna Zivkovic- Student 1
  Mladenka Kokanovic- Student 2
  Lidija Vukovic-Student 3
  Tamara Govorcin-Student 4

Veb aplikacija apotekarskog sistema cija je osnovna namena predstavljanje apoteka, online zakazivanje pregleda i savetovanja, kao i rezervacija lijekova.

Tehnologije koje korisimo:
  -Backend: Java + Spring Boot
  -Frontend: Vue.js + Axios
  -RDBMS: PostgreSQL
  -Integracioni server: TravisCI
  -Platforma for deployment: Heroku
  -Alat za analizu kvaliteta koda: SonarCloud
  -Application building tool: Maven


Za uspesno pokretanje potrebno je instalirati:
  -Java SDK v1.8
  -Apache Maven v3.6.3 (npr Eclipse)
  -NodeJS v12.13.0
  -PostgreSQL v13.0
  -VueJS v4.5.9
  -axios
  -vue-bootstrap

Pokretanje projekta
  -Prvo je potrebno napraviti kopiju ovog repozitorijuma sa git clone
  -Pre pokretanja projekta potrebno je imati instaliranu PostgreSQL bazu verzije 13
  -Importovati projekat kao Maven project
  -Pokretanje front-enda:
    -pozicioniranje na FrontEnd folder u CommandWindow konzoli
    -npm install
    -npm run serve  
  -Desni klik na klasu IsaProjectApplication.java -> run
