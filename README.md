# Roguelike
A simple library for creating roguelikes. This library was primarily created to practise software testing.

## Installing
### Install dependencies
```
mvn install
```
Alternatively in Eclipse follow these steps...
````
right-click project -> Run As -> Maven Install
````
### Install correct version of JRE
in Eclipse...
```
right click project -> preferences -> Java Build Path -> Select JRE System Library -> Edit... -> make sure Execution environment is set to "JavaSE-1.8 (Java SE 9 [1.8.0_121])"
```
## Running Tests
In Eclipse...
```
right-click project -> Run As -> JUnit Test
```
Alternatively with Maven and surefire...
```
right-click project -> Run As -> Maven Test
```

## Compiling
With Maven in eclipse...
```
right-click project -> Run As -> Maven Build... -> write "compile" in goals field -> Run
```
