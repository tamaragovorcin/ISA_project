ISA

[![Build Status](https://travis-ci.com/tamaragovorcin/ISA_project.svg?branch=develop)](https://travis-ci.com/tamaragovorcin/ISA_project)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=tamaragovorcin_ISA_project&metric=alert_status)](https://sonarcloud.io/dashboard?id=tamaragovorcin_ISA_project)


Fakultet tehnickih nauka Novi Sad
Primenjene racunarske nauke
Projekat iz predmeta Internet softverske arhitekture

Travis: https://travis-ci.com/github/tamaragovorcin/ISA_project

Autori:
  Luna Zivkovic- Student 1\n
  Mladenka Kokanovic- Student 2\n
  Lidija Vukovic-Student 3\n
  Tamara Govorcin-Student 4\n

Veb aplikacija apotekarskog sistema cija je osnovna namena predstavljanje apoteka, online zakazivanje pregleda i savetovanja, kao i rezervacija lijekova.\n

Tehnologije koje korisimo:\n
  -Backend: Java + Spring Boot\n
  -Frontend: Vue.js + Axios\n
  -RDBMS: PostgreSQL\n
  -Integracioni server: TravisCI\n
  -Platforma for deployment: Heroku\n
  -Alat za analizu kvaliteta koda: SonarCloud\n
  -Application building tool: Maven\n


Za uspesno pokretanje potrebno je instalirati:\n
  -Java SDK v1.8\n
  -Apache Maven v3.6.3 (npr Eclipse)\n
  -NodeJS v12.13.0\n
  -PostgreSQL v13.0\n
  -VueJS v4.5.9\n
  -axios\n
  -vue-bootstrap\n

Pokretanje projekta
  -Prvo je potrebno napraviti kopiju ovog repozitorijuma sa git clone\n
  -Pre pokretanja projekta potrebno je imati instaliranu PostgreSQL bazu verzije 13\n
  -Importovati projekat kao Maven project\n
  -Pokretanje front-enda:\n
    -pozicioniranje na FrontEnd folder u CommandWindow konzoli\n
    -npm install\n
    -npm run serve  \n
  -Desni klik na klasu IsaProjectApplication.java -> run\n
