#!/bin/sh

cd pokedeck;
mvn compile
mvn exec:java -Dexec.mainClass="upmc.pcg.controller.Pokedeck"
cd ..
