fastlane documentation
================
# Installation

Make sure you have the latest version of the Xcode command line tools installed:

```
xcode-select --install
```

Install _fastlane_ using
```
[sudo] gem install fastlane -NV
```
or alternatively using `brew install fastlane`

# Available Actions
## Android
### android clean
```
fastlane android clean
```
Delete build folder
### android compile
```
fastlane android compile
```
Compile project
### android lint
```
fastlane android lint
```
Perform Android lint analysis
### android bundle
```
fastlane android bundle
```
Build Release App Bundle
### android internal
```
fastlane android internal
```
Distribute to Internal App Sharing

----

This README.md is auto-generated and will be re-generated every time [fastlane](https://fastlane.tools) is run.
More information about fastlane can be found on [fastlane.tools](https://fastlane.tools).
The documentation of fastlane can be found on [docs.fastlane.tools](https://docs.fastlane.tools).
