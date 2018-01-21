# jD3Sankey

Build cool d3 sankey diagrams using a REPL.

![Alt text](https://raw.githubusercontent.com/prathik/jd3sankey/master/ss.png?raw=true "Optional Title")

# Features

* Create a Sankey diagram
* Store Sankey diagram as html (you can show it to others via github pages)
* Read from an existing Sankey json
* Publish to github gist and view on bl.ocks.org

# Examples

* [Split of effort taken to build jD3Sankey](https://bl.ocks.org/prathik/f3549b7a6199899f0270d3f33174a0a0)
* [Household income, savings and expenses](https://bl.ocks.org/prathik/faffdbdffefc1061dd76d8ba5865254f)

# How to use

```bash
mvn package
cd target
java -jar jd3sankey-1.0-SNAPSHOT.jar
```

After entering the commands

```bash
open index.html
```

# Usecase

Create the file locally by adding nodes and links, writing and opening the index.html generated on the browser. Everytime you write refresh the browser and see the changes. After you are happy with the generated sankey publish the chart to bl.ocks.org.

# Example

Generating the Sankey diagram above.

```
$ java -jar jd3sankey-1.0-SNAPSHOT.jar                              
Commands
add <node-name> | example: add income
link <source-node> <target-node> <value> | example link income savings 100000
write | writes the data file
help | lists the commands
q | plain quit
Enter a command and press enter
add usera
Enter a command and press enter
add userb
Enter a command and press enter
add income
Enter a command and press enter
link usera income 500000
Enter a command and press enter
link userb income 500000
Enter a command and press enter
write
Enter a command and press enter
add mutualfunds
Enter a command and press enter
link income mutualfunds 100000
Enter a command and press enter
write
Enter a command and press enter
add property
Enter a command and press enter
link income property 200000
Enter a command and press enter
write
Enter a command and press enter
add fun
Enter a command and press enter
link income fun 100000
Enter a command and press enter
add rent
Enter a command and press enter
link income rent 30000
Enter a command and press enter
write
Enter a command and press enter
add car
Enter a command and press enter
link income car 70000
Enter a command and press enter
write
Enter a command and press enter
add btc
Enter a command and press enter
link income btc 500000
Enter a command and press enter
write
Enter a command and press enter
add doge
Enter a command and press enter
link btc doge 250000
Enter a command and press enter
add lite
Enter a command and press enter
link btc lite 250000
Enter a command and press enter
write
Enter a command and press enter
q
```
