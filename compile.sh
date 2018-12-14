#!/bin/sh

rm projet.jar
cd src
javac eu/swnw/Main.java eu/swnw/networks/*.java eu/swnw/networks/edges/*.java eu/swnw/networks/nodes/*.java
jar cfm ../projet.jar META-INF/MANIFEST.MF eu/swnw/Main.class eu/swnw/networks/*.class eu/swnw/networks/edges/*.class eu/swnw/networks/nodes/*.class
