---
title: Lagom 1.4.0 released!
date: 2018-01-25
author_github: TimMoore
tags: news
summary: >
    We are pleased to announce the immediate availability of Lagom 1.4.0!
---

We are pleased to announce the immediate availability of Lagom 1.4.0!

Lagom 1.4 brings many internal updates, small features, and other improvements:

- Akka, Play, Slick and many other components included with Lagom have been updated to the latest versions, bringing along many new features and improvements.
- The Lagom TestKit now supports testing services that use RDBMS persistence.
- Scala users can write read-side processors using Slick.
- Akka HTTP replaces Netty as the default server engine in Lagom (Netty is still supported).
- Scala 2.12 support, providing smaller, better optimized deployment artifacts even for Java users (2.11 is still supported).
- A new and growing set of ["Lagom Recipes"](https://github.com/lagom/lagom-recipes) providing code examples for common use-cases.
- ...and many more smaller changes. A list of all changes since 1.3 or 1.4.0-RC1 can be found in the [Change Log](/changelog.html).

## What to do next:

- To start a Lagom 1.4 project right away, visit the [Try Lagom](/get-started.html) page.
- If you are currently using an earlier version of Lagom 2.5, check the migration guide for [Java](/documentation/1.4.x/java/Migration14.html) or [Scala](/documentation/1.4.x/scala/Migration14.html).


## Credits

Thanks to the community for their detailed bug reports and contributions.

Thanks to [Lightbend](https://www.lightbend.com) for their continued sponsorship of the Lagom core team's efforts. Lightbend offers [commercial support](https://www.lightbend.com/subscription) for Lagom.

Special thanks to the following contributors who helped with this release:

**TODO: Update after merging & backporting open PRs**

```
   63    3918    2650 Ignasi Marimon-Clos
   57    1966    1573 Tim Moore
   41    4381    2819 Renato Cavalcanti
   21    3008    1096 James Roper
    9    1040     655 Ruth Stento
    7       7       7 Play Team
    7     266     142 Elijah Rippeth
    5     296     153 Greg Methvin
    5      55     141 Yuliana Apaza
    5      31      53 Markus Jura
    4      54       5 Jules Ivanic
    3       5       5 Ben McCann
    3     531     499 Marcos Pereira
    3      53      52 Pallavi Singh
    2       2       2 t-h-a-l-i-s
    2       6       6 Sarun Rattanasiri
    2      36      39 Jirka Kremser
    2      40      62 Himani Arora
    2      25       6 Carlos R. Feliz
    2       2       2 mihbor
    2       4       2 dr3s
    2      67      29 Wayne Wang
    2      84      12 Edouard Kaiser
    2      37       1 Micho
    1       3       3 Evander Otieno
    1      77      12 Mick Jermsak Jermsurawong
    1      10       1 Yura Taras
    1      68       0 Miel Donkers
    1      75      16 datalchemist
    1       1       1 Scott Langley
    1      85      39 Vladimir Shin
    1      17       1 HSalaila
    1      19       0 Edmondo Porcu
    1       1       1 Stijn De Haes
    1       5       6 Jaie Wilson
    1       1       1 Toshiyuki Takahashi
    1       1       1 Ashish Tomer
    1       1       1 Tim Sheils
    1     309      21 Julien Jean Paul Sirocchi
    1      39       8 Neelaksh
    1       2       2 NOUGUIER Olivier
    1       1       1 Jimin Hsieh
    1      24       6 Olivier Deckers
    1       1       1 Shifeng Zhang
    1       6       0 Saniya Tech
    1     196      12 Matthias Kurz
    1       2       2 surya prakash singh
    1       3       1 Renato  Cavalcanti
    1       1       2 Dominik Kunz
    1     222      78 Pawel
    1       1       1 Brent Eritou
    1       1       1 Oliver Wickham
    1      71       8 Adam Chaulk
    1       1       1 Harsh Sharma
    1       4      36 Christopher Hunt
```
