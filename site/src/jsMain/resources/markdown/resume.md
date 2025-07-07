---
root: .common.components.layouts.MarkdownLayout(title = "Resume")
---

[//]: # (TODO: Prepare the resume)

# **Ellet**

**Flutter Developer | Scalable Apps & Packages**

Dedicated Flutter developer skilled in creating high-quality apps, packages, and platform-specific plugins.
Focused on delivering fewer, **well-executed** features, emphasizing clean, maintainable, and **tested code**.
Committed to continuous improvement and collaborative progress.

> [!NOTE]
> This resume is actively maintained and regularly updated to reflect ongoing improvements and contributions.
Expect continued refinement in structure, clarity, and detail over time.

---

## Skills

### Programming Languages

Dart, Kotlin, Java, JavaScript, TypeScript, Swift

### Frameworks & Platforms

- Cross-Platform: Flutter, Kotlin Multiplatform, Compose Multiplatform
- Android: Native development (Java/Kotlin), Jetpack Compose, XML-based UI
- Backend: Ktor, Node.js, Express.js
- Web: React, Kobweb

### Testing & Quality Assurance

- Unit and integration testing with mocking and stubbing dependencies
- End-to-end (E2E) testing to verify complete user workflows
- Continuous integration pipelines for reliable delivery

### Version Control & Collaboration

- Git and GitHub workflows, code reviews, documentation maintenance

### Development Tools & Formats

- IDEs: IntelliJ IDEA, Android Studio, VS Code
- Data formats: Markdown, JSON, XML, HTML

### Cloud & Services

- Firebase: Authentication, Firestore, Cloud Functions, Storage

### Software Architecture

- Layered and Clean Architecture for scalable, maintainable apps

### Soft Skills

- Team collaboration, communication, adaptability

---

## **Experience**

### *[Flutter Quill](https://github.com/singerdmx/flutter-quill) Maintainer* | *Sep 2023 – Present*

- Enhanced **image save functionality** with tests, ensuring robust and reliable
  behavior ([#2403](https://github.com/singerdmx/flutter-quill/pull/2403), [quill-native-bridge#9](https://github.com/FlutterQuill/quill-native-bridge/pull/9))
- Developed [`quill_native_bridge`](https://pub.dev/packages/quill_native_bridge), a Flutter plugin for Flutter Quill to
  access platform-specific functionalities
- Resolved **common build failures** and introduced native clipboard functionality for rich text paste support,
  eliminating the need for Rust
  installation ([#2230](https://github.com/singerdmx/flutter-quill/pull/2230), [initial commit](https://github.com/FlutterQuill/quill-native-bridge/commit/3165de2b4e2c43b32cdf425c3b12ed62545ad030), [#2322](https://github.com/singerdmx/flutter-quill/pull/2322))
- Addressed **critical bugs** by reverting unstable features to maintain project stability and ensure a smooth user and
  developer experience ([#2413](https://github.com/singerdmx/flutter-quill/pull/2413))
- Published the **v11 pre-release**, delivering major improvements and resolving common issues without introducing new
  features ([#2338](https://github.com/singerdmx/flutter-quill/pull/2338))  
  Added [migration guide](https://github.com/singerdmx/flutter-quill/blob/master/doc/migration/10_to_11.md)
- Added **Flutter/WASM support** ([#2293](https://github.com/singerdmx/flutter-quill/pull/2293))
- Conducted **PR reviews** to maintain code quality and support community
  contributors ([#2415](https://github.com/singerdmx/flutter-quill/pull/2415))
- Fixed bugs to improve stability and correctness (e.g., [#2522](https://github.com/singerdmx/flutter-quill/pull/2522), [#2279](https://github.com/singerdmx/flutter-quill/pull/2279))
- Refactored confusing logic with full backward compatibility and added tests to ensure reliability ([example #2525](https://github.com/singerdmx/flutter-quill/pull/2525))
- Filed bug issues with steps to reproduce (e.g., [Back Button issue](https://github.com/singerdmx/flutter-quill/issues/2527), [macOS missing keyboard actions](https://github.com/singerdmx/flutter-quill/issues/2288))
- Filed feature requests with details (e.g., [Spell Checking](https://github.com/singerdmx/flutter-quill/issues/2246), [Replacing a discounted dependency](https://github.com/singerdmx/flutter-quill/issues/2290))
- Removed a dependency that might cause issues with YouTube's terms of service ([#2286](https://github.com/singerdmx/flutter-quill/pull/2286))
- Comprehensive
  contributions: [All PRs](https://github.com/singerdmx/flutter-quill/pulls?q=is%3Apr+author%3AEchoEllet) | [Commits](https://github.com/singerdmx/flutter-quill/commits/master/?author=EchoEllet)

---

## **Guest Contributions / Activities**

### **[Flutter](https://github.com/flutter/packages)**

Implemented native image picking support for macOS as part of a broader effort to
extend the [`image_picker`](http://pub.dev/packages/image_picker) plugin to desktop platforms:

- Submitted [PR #8079](https://github.com/flutter/packages/pull/8079) with full implementation, tests, and
  documentation
- The PR was [closed](https://github.com/flutter/packages/pull/8079#issuecomment-2578911082) due to project priorities,
  but the work was recognized and maintainers encouraged publishing it as
  a [standalone package](https://pub.dev/packages/native_image_picker_macos)

### *[firebase_storage Flutter Package](https://pub.dev/packages/firebase_storage)*

[Fixed a rare bug on Android](https://github.com/firebase/flutterfire/pull/12047) with
[unit tests and code cleanup](https://github.com/firebase/flutterfire/pull/17484)

### **[Flutter BLoC](https://bloclibrary.dev/)**

- Contributed the [“Modeling State” documentation page](https://bloclibrary.dev/modeling-state/) ([PR #4201](https://github.com/felangel/bloc/pull/4201))
- Fixed pub.dev platform tags to accurately reflect supported platforms, which is a minor improvement ([PR #3993](https://github.com/felangel/bloc/pull/3993))

### *[macos_ui Flutter Package](https://pub.dev/packages/macos_ui)*

[Support for Swift Package Manager](https://github.com/macosui/macos_window_utils.dart/pull/66)

### *[serverpod Flutter Package](https://pub.dev/packages/serverpod_flutter)*

[A minor refactoring to simplify implementation details](https://github.com/serverpod/serverpod/pull/3439/files) while
unit testing

### *[Kobweb](https://github.com/varabyte/kobweb)*

Listed in the [Kobweb Guest Contributors](https://kobweb.varabyte.com/docs/community/contributors#guest-contributors)

### *[Ktor Documentation](https://github.com/ktorio/ktor-documentation)*

[Using ProGuard to minimize Ktor server applications](https://github.com/ktorio/ktor-documentation/pull/481/files)

### **[ATLauncher](https://github.com/ATLauncher/ATLauncher)**

Quick play feature to join a Minecraft server, world, and realm on game launch ([#893](https://github.com/ATLauncher/ATLauncher/pull/893))

---

## **Projects**

### **[Flutter Quill](https://github.com/singerdmx/flutter-quill/)** · Rich Text Editor for Flutter · ⭐ [2.8k](https://github.com/singerdmx/flutter-quill) · 📦 [146K+ Downloads](https://pub.dev/packages/flutter_quill)

A powerful WYSIWYG editor for Flutter, built with the same JSON document format as [QuillJS](https://quilljs.com/).  
Supports rich text editing across mobile, desktop, and web platforms with customizable UI and platform integration capabilities.

### **[Native macOS Flutter Image Picker](https://github.com/CompileKernel/native-image-picker-macos)**

A native macOS implementation of [`image_picker`](https://pub.dev/packages/image_picker), using the native image picker
instead of the open file dialog

### **[Kraft Launcher](https://github.com/KraftLauncher/kraft-launcher)** | Minecraft Launcher

An open-source Minecraft Java launcher focused on instance isolation, seamless sharing, and mod management.

Actively developed with a strong focus on maintainability, testability, and clean architecture,  
backed by a [documented app structure](https://github.com/KraftLauncher/kraft-launcher/blob/main/docs/ARCHITECTURE.md) and  
[a comprehensive test suite](https://github.com/KraftLauncher/kraft-launcher/tree/main/test/account/logic) for core features.

### **[Quill Native Bridge](https://pub.dev/packages/quill_native_bridge)** | Flutter Plugin

A platform package for [`flutter_quill`](https://pub.dev/packages/flutter_quill) to access native APIs

Originally submitted to the Flutter ecosystem  
([pull request](https://github.com/flutter/packages/pull/8079)), later split into a community package

## **Side Projects**

### **[Gym App MVP](https://github.com/EchoEllet/gym-app-prototype)** | App for Gym Members

An MVP combining [Kobweb](https://github.com/varabyte/kobweb), [Ktor](https://ktor.io/),
and [Flutter](https://flutter.dev/)

[Download the Flutter admin app](https://drive.google.com/file/d/1SntZE2yHYe4HgFEWOmR1h-wwg33FATd4/view?usp=sharing)

[View the app as a gym client](https://freshkernel.dev/login?userId=67fc361c40388d1ee512fdff&loginToken=SzkZ1VLkZwbvBIOcYuSEGNHwVGjV1VjPhYODIwETik8)

### **[Kraft Sync](https://github.com/FreshKernel/kraft-sync/)** | Minecraft Script

Automates mod/resource pack/server sync upon game launch.  
Includes an admin utility app for management.  
Written in [Kotlin/JVM](https://kotlinlang.org/docs/jvm-get-started.html) and optimized
with [ProGuard](https://github.com/Guardsquare/proguard)

### **[Alrayada Web](https://github.com/FreshKernel/alrayada-web)**

A production website for a company using [Kobweb](https://github.com/varabyte/kobweb)

### **[Firebase App Check for Ktor Server](https://github.com/FreshKernel/ktor-server-firebase-app-check)**

A [Ktor](https://ktor.io/) server plugin to support [Firebase App Check](https://firebase.google.com/docs/app-check)

---

## **Contact**

- **GitHub**: [github.com/EchoEllet](https://github.com/EchoEllet)
- **Email**: [echo.ellet@gmail.com](mailto:echo.ellet@gmail.com)
