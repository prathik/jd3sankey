# jd3sankey

Build cool d3 sankey diagrams using a REPL.



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

# Example

```
$ java -jar jd3sankey-1.0-SNAPSHOT.jar                              1 ↵
Commands
add <node-name> | example: add income
link <source-node> <target-node> <value> | example link income savings 100000
write | writes the data file and quit
help | lists the commands
q | plain quit
Enter a command and press enter
add usera
Enter a command and press enter
add userb
Enter a command and press enter
add income
Enter a command and press enter
link prathik income 500000
Enter a command and press enter
link lakshmi income 500000
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
