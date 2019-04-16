# [Language Based Security] Tools for race detection in Java

## Background

Multithreaded programs are common and useful, but can often be difficult to
implement correctly, and data races introduced by programming errors may make
an application or system vulnerable to attack. Due to the unpredictability of
how code is interleaved in a multithreaded environment, finding these types of
vulnerabilities through testing is often difficult, and concurrency bugs that
do occur can often be very hard to reproduce. Doing an exhaustive analysis of
all possible interleavings is generally not a feasible option either, due to
how quickly the total number of such interleavings grows as the number of
instructions and threads increases. Nevertheless, various methods for
identifying potential races do exist, and are used by a number of different
tools.

## The goal of the project

The goal of the project is to investigate and compare a set of different tools
for data race detection in Java. We will be performing a brief review of the
specifics of concurrency in Java, including potential risks as well as common
concurrency patterns. We will then create a set of benchmarks based on some of
these vulnerabilities and concurrency patterns, to see how the different tools
perform in terms of false positives and false negatives. Additionally, in cases
where there are known limitations of a tool, we may create additional
benchmarks based on these limitations to see whether the other tools are able
to handle them.

The main tools we will focus on are
[RacerD](https://fbinfer.com/docs/racerd.html), which is part of Facebook’s
Infer tool, and the GuardedBy analysis provided by Google’s
[Error Prone analyser](https://github.com/google/error-prone/blob/master/docs/bugpattern/GuardedBy.md),
as we believe that these types of tools are more likely to be adopted for
practical use by developers than more experimental tools providing more
advanced analyses. However, if we can find a suitable candidate, we also intend
to include one tool that is more experimental in our analysis to use as a
comparison. Potential candidates for tools of this type include
[Chord](https://www.seas.upenn.edu/~mhnaik/chord/user_guide/whatis.html) and
[Race Detector & Healer for Java](http://www.fit.vutbr.cz/research/groups/verifit/tools/racedetect/index.php).

## Relevance to language based security

Because data races, such as *time-of-check, time-of-use* race conditions may
open up an application or system to potential attacks, it is of interest from
a security perspective to be able to identify such races before they can be
exploited by an actual attacker. Different tools for doing so are likely to
have different strengths and weaknesses, and may differ in ease-of-use and
rates of false positives and negatives. Having a better idea of various tools’
capabilities and tradeoffs may thus help in preventing vulnerabilities caused
by data races in Java by making it easier to choose the right tool for the job,
as well as pinpointing potential blind spots that may require extra attention
or the use of a different tool.

## Building and running

This project uses the
[Gradle Wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html)
to build and run the code used for the various benchmarks. To use it, simply
run:

    $ ./gradlew <command>

in the top-level directory of this project (where the files `gradlew` and
`gradlew.bat` are located), no manual installation required!

To build the project, run:

    $ ./gradlew build

To run the project, run:

    $ ./gradlew run

To clean the project, run:

    $ ./gradlew clean


### SpotBugs (FindBugs)

To run the SpotBugs plugin on the project code, run:

    $ ./gradlew spotbugsMain

The generated reports will be found in `build/reports/spotbugs`.

### Error Prone

To compile using the Error Prone plugin, run:

    $ ./gradlew errorproneCompile

Any errors found will be displayed as compiler warnings in the command line
output.

### SonarQube

To run the SonarQube plugin, first
[install and run](https://docs.sonarqube.org/latest/setup/get-started-2-minutes/)
the SonarQube server. With the server running, run:

    $ ./gradlew sonarqube

Results can be seen by visiting `http://localhost:9000/dashboard?id=jrdb` while
the server is running.
