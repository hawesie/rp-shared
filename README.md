# Robot Programming Shared Classes

These are some classes useful for various leJOS-related tasks in the Robot Programming module in the School of Computer Science, University of Birmingham.

## Eclipse

### Getting the code

To use this code in your project you first need to clone the project into your Eclipse workspace. The following assumes you use the directory `~/workspace` as your Eclipse workspace as it is the default value. If this is not true, then replace this directory with the correct one for you. 

1. Open a terminal
2. Change into your workspace directory: `cd ~/workspace`
3. Clone this project using Git: `git clone https://github.com/hawesie/rp-shared`
4. In Eclipse, create a new leJOS NXT project with the name `rp-shared`. This should automatically find the sources you just cloned. You could also create a leJOS PC project for this code, but by creating an NXT project you ensure that you don't accidentally add code here that won't run on the robot.

### Using the code

You should develop your own code in a *separate project* to `rp-shared` as this will allow you to easily update the provided code if necessary. To do this, use the `Java Build Path` entry in your other project's properties, and `Add...` the `rp-shared` project under the `Projects` tab.

## Command Line

If you just want to use the code in compilation (e.g. from the command line) without Eclipse, you can download jar and use it in your classpath: https://raw.githubusercontent.com/hawesie/rp-shared/master/export/rp-shared.jar


## Fixing bugs

If you find any bugs in my code, please open an [issue](https://github.com/hawesie/rp-shared/issues) or create a pull request with the fix.