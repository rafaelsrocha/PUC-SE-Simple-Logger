# Simple Logger 
[![Language](https://img.shields.io/badge/Java-1.8-green.svg?style=flat)](https://java.com/)
[![JUnit](https://img.shields.io/badge/JUnit-4.12-yellowgreen.svg?style=flat)](https://junit.org/junit4/)

This is a simple library concept created to organize and log whatever you need on predefined environments. Created as a final project of the _Project Patterns and Frameworks_ Master's class to apply design patterns' principles in Java.

## Installation

### Requirements
* Java 1.8
* JUnit 4.12
* Eclipse IDE

Clone this repo and import as an [existing Maven Project](https://stackoverflow.com/a/36242422). Everything should be fine.

## Usage

You can use with lib's default settings:

```
Logger logger = new Logger().build();
```
... or building your own settings:
```java
String filePath = "./my-file-path";
Logger logger = new Logger()
  		.logType(LogType.FILE)
  		.logLevel(LogLevel.ERROR)
  		.filePath(filePath)
  		.logFormat(LogFormat.PRETTY_PRINTED)
  		.build();
```
Just **remember** to use `.build()` to build your `Logger`.
After building it, you can simply
```
logger.log(LogLevel.INFO, "My info log.");
```
passing the `LogLevel` and a message to log.

### Types

#### LogLevel

- `DEBUG` (default)
- `INFO`
- `WARNING`
- `ERROR`
- `FATAL`

#### LogType

- `DATABASE`
  - Configurable attribute:
    - `databaseName`: default is `"mysql2"`
- `HTTP`
  - Configurable attribute:
    - `httpUri`: default is `"http://localhost:8080"`
- `FILE`
  - Configurable attribute:
    - `filePath`: default is `"./output.log"`
- `CONSOLE` (default)

#### LogFormat

- `CLEAN`
  - will format as `TIMESTAMP - LEVEL - MESSAGE`
- `DEFAULT` (default) _(obviously)_
  - will format as `LEVEL : TIMESTAMP : DESTINATION : MESSAGE`
- `PRETTY_PRINTED`
  - will format as `[LEVEL] : TIMESTAMP : LOGGER_NAME : DESTINATION : MESSAGE`

## License
[MIT](https://choosealicense.com/licenses/mit/)