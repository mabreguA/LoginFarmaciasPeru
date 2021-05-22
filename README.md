# README #

This README would normally document whatever steps are necessary to get your application up and running.

### What is this repository for? ###

* Quick summary
* Version
* [Learn Markdown](https://bitbucket.org/tutorials/markdowndemo)

### How do I get set up? ###

* Summary of set up
* Configuration
* Dependencies
* Database configuration
* How to run tests
* Deployment instructions

### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines

### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact


### Execution Report
* Go to Terminal console
* Write :  mvn verify test -Dcucumber.options="--tags @TagName"

Example: Running scenarios which match @important OR @billing
cucumber --tags @billing,@important

Example: Running scenarios which match @important AND @billing
cucumber --tags @billing --tags @important

https://docs.cucumber.io/cucumber/api/#tags
http://meridadesignblog.com/configurar-variables-entorno-path-en-mac-os-x/
touch ~/.bash_profile
open ~/.bash_profile
export PATH=${PATH}:/tu_ruta
source ~/.bash_profile


mvn verify test -Dcucumber.options="--tags @EmisionHogarIndividual"
mvn verify test -Dcucumber.options="--tags @EmisionHogarColectivo"
mvn clean test -Dcucumber.options="--tags @EmisionHogar"