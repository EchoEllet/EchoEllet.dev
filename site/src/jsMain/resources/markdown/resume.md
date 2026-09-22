---
root: .common.components.layouts.MarkdownLayout(title = "Resume")
---

[//]: # (TODO: Polish the resume further)

# **Ellet**

**Software engineer | Flutter Developer & Open Source Contributor**

Software engineer focused on creating well-executed software. Prefer delivering fewer but well-developed features
(clean, documented, readable, maintainable, and tested code) over a larger number of less polished ones. Experienced in
developing and maintaining Flutter applications, libraries, and open-source projects.

---

## Skills

### Programming Languages

Dart, Kotlin, Java, JavaScript, TypeScript, and Swift.

### Frameworks & Platforms

- Flutter.
- Kotlin Multiplatform, Compose Multiplatform.
- Android: Jetpack Compose, XML.
- Web
  - backend: Ktor, Dart Shelf, Node.js, Express.js.
  - frontend: Kobweb, React. 

### Software Architecture

- Software API design and evolution with a focus on clarity, consistency, and backward compatibility.
- Refactoring complex or confusing implementations while preserving existing behavior.
- Designing modular, maintainable, and testable software with clear separation of concerns.

### Testing & Quality Assurance

Unit, integration, and end-to-end testing.

### Cloud Services

Firebase: Authentication, Firestore, Cloud Functions, and Storage.

---

## Experience

### **[Flutter Quill](https://github.com/singerdmx/flutter-quill) Maintainer** | **Sep 2023 – Jul 2025**

A rich text editor for Flutter using the same JSON document format as [QuillJS](https://quilljs.com/), supporting
mobile, desktop, and web platforms.

[**2.9k GitHub stars**](https://github.com/singerdmx/flutter-quill/) ·
[**247K+ downloads**](https://pub.dev/packages/flutter_quill) ·
[**Used in a 5-hour freeCodeCamp course**](https://youtu.be/F6P0hve2clE?t=13814)

- Enhanced **image save functionality** with tests, ensuring robust and reliable
  behavior: [#2403](https://github.com/singerdmx/flutter-quill/pull/2403), [quill-native-bridge#9](https://github.com/FlutterQuill/quill-native-bridge/pull/9).
- Developed [`quill_native_bridge`](https://pub.dev/packages/quill_native_bridge), a Flutter plugin for Flutter Quill to
  access platform-specific functionalities.
- Resolved **common build failures** and introduced native clipboard functionality for rich text paste support,
  eliminating the need for Rust
  installation ([#2230](https://github.com/singerdmx/flutter-quill/pull/2230), [initial commit](https://github.com/FlutterQuill/quill-native-bridge/commit/3165de2b4e2c43b32cdf425c3b12ed62545ad030), [#2322](https://github.com/singerdmx/flutter-quill/pull/2322)).
- Addressed **critical bugs** by reverting unstable features to maintain project stability and ensure a smooth user and
  developer experience ([#2413](https://github.com/singerdmx/flutter-quill/pull/2413)).
- Published the **v11 pre-release**, delivering major improvements and resolving common issues without introducing new
  features ([#2338](https://github.com/singerdmx/flutter-quill/pull/2338)).  
  Added [migration guide](https://github.com/singerdmx/flutter-quill/blob/master/doc/migration/10_to_11.md).
- Added **Flutter/WASM support** ([#2293](https://github.com/singerdmx/flutter-quill/pull/2293)).
- Conducted **PR reviews** to maintain code quality and support community
  contributors ([#2415](https://github.com/singerdmx/flutter-quill/pull/2415)).
- Fixed bugs to improve stability and correctness (e.g., [#2522](https://github.com/singerdmx/flutter-quill/pull/2522), [#2279](https://github.com/singerdmx/flutter-quill/pull/2279)).
- Refactored confusing logic with full backward compatibility and added tests to ensure reliability ([example #2525](https://github.com/singerdmx/flutter-quill/pull/2525)).
- Filed bug issues with steps to reproduce (e.g., [Back Button issue](https://github.com/singerdmx/flutter-quill/issues/2527), [macOS missing keyboard actions](https://github.com/singerdmx/flutter-quill/issues/2288)).
- Filed feature requests with details (e.g., [Spell Checking](https://github.com/singerdmx/flutter-quill/issues/2246), [Replacing an unmaintained dependency](https://github.com/singerdmx/flutter-quill/issues/2290)).
- Removed a dependency that might cause issues with YouTube's terms of service ([#2286](https://github.com/singerdmx/flutter-quill/pull/2286)).
- Contribution history: [All PRs](https://github.com/singerdmx/flutter-quill/pulls?q=is%3Apr+author%3AEchoEllet) | [Commits](https://github.com/singerdmx/flutter-quill/commits/master/?author=EchoEllet).

---

## Open Source Contributions

### **[Flutter](https://github.com/flutter/packages)**

- Implemented native image picking support for macOS as part of a broader effort to
  extend the [`image_picker`](http://pub.dev/packages/image_picker) plugin to desktop platforms. Submitted [PR #8079](https://github.com/flutter/packages/pull/8079) with full implementation, tests, and
  documentation.
  - The PR was [closed](https://github.com/flutter/packages/pull/8079#issuecomment-2578911082) due to project priorities,
    but the work was recognized and maintainers encouraged publishing it as
    a [standalone package](https://pub.dev/packages/native_image_picker_macos).
- Submitted a [detailed issue report](https://github.com/flutter/flutter/issues/174205)
  with reproduction steps, investigation, and supporting evidence. Report [acknowledged](https://github.com/flutter/flutter/issues/174205#issuecomment-3213572807) by a Flutter maintainer as
  detailed and reproducible.

### **[flutter_secure_storage](https://pub.dev/packages/flutter_secure_storage)** | Flutter package

- Developed [
  `flutter_secure_storage_linux_secret_service`](https://pub.dev/packages/flutter_secure_storage_linux_secret_service),
  a pure-Dart Linux implementation that communicates directly with the standard Secret Service API over D-Bus.
  - Maintains interoperability with existing secrets without requiring code changes to consuming apps or packages.
  - Removes the need for Flutter method channels, C++/GObject, and native system libraries (`libsecret`,
    `libjsoncpp`), reducing integration complexity.
  - Developed [`freedesktop_secret`](https://pub.dev/packages/freedesktop_secret), a decoupled Dart client
    for the Secret Service API used by this package. This lower-level package can be used independently of
    `flutter_secure_storage` ([example](https://github.com/Skyost/SimpleSecureStorage/pull/15)).
    More details in [this GitHub discussion](https://github.com/EchoEllet/dart-packages/discussions/3).
  - Originally developed as a complete rewrite of `flutter_secure_storage_linux`
    in [#1182](https://github.com/juliansteenbakker/flutter_secure_storage/pull/1182). The PR was not merged
    ([reason](https://github.com/juliansteenbakker/flutter_secure_storage/issues/1203#issuecomment-5624556469)), but
    the implementation
    was [recognized](https://github.com/juliansteenbakker/flutter_secure_storage/pull/1182#issuecomment-5043235409) by
    the maintainer as "pretty solid".
  - The package was [referenced](https://pub.dev/packages/flutter_secure_storage#alternative-implementations) in the
    `flutter_secure_storage` README under the "Alternative implementations" section.
  - Identified and reported a historical upstream bug in the Linux implementation
    in [#1181](https://github.com/juliansteenbakker/flutter_secure_storage/issues/1181).
- Developed [`flutter_secure_storage_linux_portal`](https://pub.dev/packages/flutter_secure_storage_linux_portal): a
  pure-Dart Linux implementation that uses the Secret Portal API to obtain a master secret and encrypts the secrets in a
  local file. Suitable for sandboxed environments (Flatpak/Snap).
  - Developed [`xdg_secret_portal_store`](https://pub.dev/packages/xdg_secret_portal_store), a decoupled Dart helper
    library used by this package. This lower-level package can be used independently of
    `flutter_secure_storage` ([example](https://github.com/Skyost/SimpleSecureStorage/pull/17)).
  - The package was [referenced](https://pub.dev/packages/flutter_secure_storage#alternative-implementations) in the
    `flutter_secure_storage` README under the "Alternative implementations" section.
- Improved the original Linux README documentation:
  [#1275](https://github.com/juliansteenbakker/flutter_secure_storage/pull/1275)
  and [#1141](https://github.com/juliansteenbakker/flutter_secure_storage/pull/1141).

### **[Firebase](https://firebase.google.com/)**

- [Fixed a rare bug](https://github.com/firebase/flutterfire/pull/12047) in the
[`firebase_storage`](https://pub.dev/packages/firebase_storage) package on Android with
[unit tests and code cleanup](https://github.com/firebase/flutterfire/pull/17484).

### **[Flutter BLoC](https://bloclibrary.dev/)**

- Added the initial ["Modeling State" documentation page](https://bloclibrary.dev/modeling-state/) ([PR #4201](https://github.com/felangel/bloc/pull/4201)).
- Fixed pub.dev platform tags to accurately reflect supported platforms, which is a minor improvement ([PR #3993](https://github.com/felangel/bloc/pull/3993)).

### **[Serverpod](https://pub.dev/packages/serverpod_flutter)** | Flutter framework

- Submitted a [detailed issue report](https://github.com/serverpod/serverpod/issues/5002)
  with reproduction steps, investigation, and supporting evidence.
  Report [acknowledged](https://github.com/serverpod/serverpod/issues/5002#issuecomment-4805275713) by a Serverpod
  maintainer as detailed.
- [A minor refactoring to simplify implementation details](https://github.com/serverpod/serverpod/pull/3439/files) while
  unit testing.

### **[http](https://pub.dev/packages/http)** | Dart package

- Updated the OkHttp integration to support the latest Flutter, Kotlin, `package:jni`, Gradle, and AGP versions, and
  fixed release-build crashes:
  [#1943](https://github.com/dart-lang/http/pull/1943) and [#1973](https://github.com/dart-lang/http/pull/1973).

### **[macos_ui](https://pub.dev/packages/macos_ui)** | Flutter package

- Added support for Swift Package
  Manager: [macos_ui#573](https://github.com/macosui/macos_ui/pull/573), [macos_window_utils#66](https://github.com/macosui/macos_window_utils.dart/pull/66),
  and [appkit_ui_element_colors#2](https://github.com/Adrian-Samoticha/appkit_ui_element_colors/pull/2).

### **[postgres](https://pub.dev/packages/postgres)** | Dart package

- Documented the `Session` abstraction and `Pool` convenience methods, clarifying their
  relationship and underlying connection behavior: [#460](https://github.com/isoos/postgresql-dart/pull/460).

### **[Kobweb](https://github.com/varabyte/kobweb)** | Kotlin web framework

Listed in the [Kobweb Guest Contributors](https://kobweb.varabyte.com/docs/community/contributors#guest-contributors).

### **[Ktor](https://ktor.io/)**

- Updated [Ktor Documentation](https://github.com/ktorio/ktor-documentation) to rewrite the ProGuard example, explaining
  how to minimize Ktor server applications: [#481](https://github.com/ktorio/ktor-documentation/pull/481).

### **[Epic Fight](https://epicfight-docs.readthedocs.io/)**

Epic Fight is a major Minecraft Java game modification with over
[**29 million downloads**](https://www.curseforge.com/minecraft/mc-mods/epic-fight-mod).

- Submitted a detailed [design document](https://github.com/Antikythera-Studios/epicfight/issues/2116) to refactor the
  input system to support controllers, improve extension support, and simplify usage.
- [Refactored input system](https://github.com/Antikythera-Studios/epicfight/pull/2122) while retaining full backward
  compatibility.
  - Quickly adapted to Minecraft's obfuscated internal APIs within a few days, despite no prior experience.
- Implemented the initial [controller compatibility after the refactoring](https://github.com/Antikythera-Studios/epicfight/pull/2133).
- [Improved and stabilized the input system's public API](https://github.com/Antikythera-Studios/epicfight/issues/2194).
- Migrated project structure to support multi-platforms
  ([#2360](https://github.com/Antikythera-Studios/epicfight/pull/2360)).
- Migrated from Gradle Groovy to Gradle KTS
  ([#2136](https://github.com/Antikythera-Studios/epicfight/issues/2136), [#2235](https://github.com/Antikythera-Studios/epicfight/pull/2235))
  and rewrote build scripts.
- Became a core contributor for several
  months: [All Issue Reports and Pull Requests](https://github.com/Antikythera-Studios/epicfight/issues?q=author%3AEchoEllet) | [Commits](https://github.com/Antikythera-Studios/epicfight/commits/1.21.1/?author=EchoEllet)
- Developed [a Gradle plugin](https://github.com/EchoEllet/mc-safe-resources-gradle) for Epic Fight and its related
  projects.
- Contributed to the Epic Fight ecosystem:
  - [Sword Soaring PRs](https://github.com/P1neapplell0/SwordSoaring-Reborn/pulls?q=is%3Apr+author%3AEchoEllet)
  - [Epic Fight Invincible PRs](https://github.com/P1neapplell0/EpicFight-Invincible/pulls?q=is%3Apr+author%3AEchoEllet)
  - [Weapons of Miracles](https://modrinth.com/mod/weapons-of-miracles). The source code is not publicly available, but
    the JAR file can be downloaded, and "Ellet" can be found in its `META-INF/neoforge.mods.toml` file as a contributor.
- Discovered and investigated [a high-memory usage issue](https://github.com/P1neapplell0/SwordSoaring-Reborn/issues/4)
  with fellow developers.
- Backported the controller modification to an older Minecraft version, and the legacy mod platform
  ([GitHub repository](https://github.com/echoEllet/controlify)), which has
  [142k+ downloads](https://www.curseforge.com/minecraft/mc-mods/controlify-forgified).

### **[ATLauncher](https://github.com/ATLauncher/ATLauncher)**

- Added the quick play feature to join a Minecraft server, world, and realm on game launch ([#893](https://github.com/ATLauncher/ATLauncher/pull/893)).

---

## Projects

### **[Dart Packages](https://github.com/EchoEllet/dart-packages)** | [pub.dev publisher](https://pub.dev/publishers/echoellet.dev/packages)

A collection of published Dart and Flutter packages, including:

- [`freedesktop_secret`](https://pub.dev/packages/freedesktop_secret)
- [`xdg_secret_portal_store`](https://pub.dev/packages/xdg_secret_portal_store)
- [`flutter_secure_storage_linux_secret_service`](https://pub.dev/packages/flutter_secure_storage_linux_secret_service)
- [`flutter_secure_storage_linux_portal`](https://pub.dev/packages/flutter_secure_storage_linux_portal)
- [`system_accent_color`](https://pub.dev/packages/system_accent_color)
- [`connectivity_plus_linux_portal`](https://pub.dev/packages/connectivity_plus_linux_portal)

Developed with a focus on clean APIs, interoperability, documentation, testing, and maintainability.

### **[Native macOS Flutter Image Picker](https://github.com/CompileKernel/native-image-picker-macos)**

A native macOS implementation of [`image_picker`](https://pub.dev/packages/image_picker), using the native image picker
instead of the open file dialog.

Originally submitted to the Flutter ecosystem  
([pull request](https://github.com/flutter/packages/pull/8079)), later split into a community package.

### **[Kraft Launcher](https://github.com/KraftLauncher/kraft-launcher)**

An open-source Minecraft Java launcher focused on instance isolation, seamless sharing, and mod management.

Developed with a strong focus on maintainability, testability, and clean architecture,  
backed by a [documented app structure](https://github.com/KraftLauncher/kraft-launcher/blob/main/docs/ARCHITECTURE.md)
and a comprehensive test suite for core features.

### **[Quill Native Bridge](https://pub.dev/packages/quill_native_bridge)**

A Flutter plugin for [Flutter Quill](https://pub.dev/packages/flutter_quill) to access platform-specific APIs.

## Side Projects

### **[Gym App](https://github.com/EchoEllet/gym-app-prototype)**

An application for gym members. Uses [Kobweb](https://github.com/varabyte/kobweb), [Ktor](https://ktor.io/),
and [Flutter](https://flutter.dev/).

### **[Kraft Sync](https://github.com/FreshKernel/kraft-sync/)**

A JVM application/script that automates mod/resource pack/server sync upon game launch.  
Includes an admin utility app for management.  
Written in [Kotlin/JVM](https://kotlinlang.org/docs/jvm-get-started.html) and optimized
with [ProGuard](https://github.com/Guardsquare/proguard).

### **[Alrayada Web](https://github.com/FreshKernel/alrayada-web)**

A landing page for a medical company using [Kobweb](https://github.com/varabyte/kobweb).

### **[Firebase App Check for Ktor Server](https://github.com/FreshKernel/ktor-server-firebase-app-check)**

A [Ktor](https://ktor.io/) server plugin to support [Firebase App Check](https://firebase.google.com/docs/app-check).

---

## Contact

- **GitHub**: [github.com/EchoEllet](https://github.com/EchoEllet)
- **Email**: [ellet@echoellet.dev](mailto:ellet@echoellet.dev)
