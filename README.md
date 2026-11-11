# GOPrint
Android app for visual programming. A program is built from blocks on an infinite canvas and executed by a built-in
interpreter.

Team project of the Higher IT School, module 7. Authors: [@rendivy](https://github.com/rendivy),
[@StaZisS](https://github.com/StaZisS), [@kubavsop](https://github.com/kubavsop).

## Features
- Blocks are placed on an infinite canvas and connected with links;
- The interpreter uses dynamic typing. Expressions are converted to reverse Polish notation before evaluation;
- Control flow: `if` / `endif`, `while`, `for`, `break`, `continue`;
- User functions: function block, end of function block, call function block;
- Console output with the `print` block;
- Debugger with breakpoints and step-by-step execution.

## Requirements
- JDK 17 or higher;
- Android SDK with API level 33 (`compileSdk 33`);
- Device or emulator with Android 7.1 (API 25) or higher (`minSdk 25`).

The project uses Gradle 8.0 (wrapper), AGP 8.0.0 and Kotlin 1.7.20.

## Build
```shell
git clone https://github.com/rendivy/GOPrint.git
cd GOPrint
./gradlew :app:assembleDebug
```

The APK is written to `app/build/outputs/apk/debug/app-debug.apk`. To build and install it on a connected device, run:

```shell
./gradlew :app:installDebug
```

## Data types
| Type | How to set |
|---|---|
| `Int` | `123` |
| `Double` | `123.6` |
| `Boolean` | `true` / `false` |
| `String` | `"your_string"` |
| `Array` | `*name_array[size_array]` |

### Type conversion
| Method | Applies to | Returns |
|---|---|---|
| `variable.toInt()` | `Boolean`, `Int`, `Double`, `String` | `Int` |
| `variable.toDouble()` | `Boolean`, `Int`, `Double`, `String` | `Double` |
| `variable.toBool()` | `Boolean`, `Int`, `Double`, `String`, `Array` | `Boolean` |
| `variable.toString()` | `Array` | `String` |
| `variable.toList()` | `String` | `Array` |

### Built-in functions
| Function | Argument types | Returns |
|---|---|---|
| `abs(variable)` | `Int`, `Double` | `Int`, `Double` |
| `exp(variable)` | `Int`, `Double` | `Double` |
| `ceil(variable)` | `Int`, `Double` | `Int` |
| `floor(variable)` | `Int`, `Double` | `Int` |
| `len(variable)` | `Array`, `String` | `Int` |
| `sorted(variable)` | `Array` | `Array` |

`Array` also has the `.sort()` method.

## Blocks
- **Initialization** - creates a variable. Variables are dynamically typed, so an initial value is required;
- **Set** - changes the value of a variable;
- **Get value** - reads a value;
- **Call function** - calls a function that returns no value;
- **If** / **Endif** - the Endif block is created together with If. It is optional and limits the scope.

| Initialization | Set | Get value | Call function | Endif |
|---|---|---|---|---|
| <img src="app/src/main/java/com/example/android_blueprint/image/initialization.png" width="150"> | <img src="app/src/main/java/com/example/android_blueprint/image/set.png" width="150"> | <img src="app/src/main/java/com/example/android_blueprint/image/get_value.png" width="150"> | <img src="app/src/main/java/com/example/android_blueprint/image/call_function.png" width="150"> | <img src="app/src/main/java/com/example/android_blueprint/image/endif.png" width="150"> |

### Operators
Binary operators: `+`, `-`, `×`, `÷`, `%`, `pow`, `log`, `==`, `!=`, `>`, `>=`, `<`, `<=`.
The top input of a binary operator block is the left operand, the bottom input is the right operand:
`top operator bottom`.

- `log` - the top input is the argument, the bottom input is the base;
- `pow` - the top input is the number, the bottom input is the exponent.

Unary operators: `abs`, `not`, `sin`, `cos`, `tg`, `ctg`, `arcsin`, `arccos`, `arctg`, `arcctg`.

<img src="app/src/main/java/com/example/android_blueprint/image/binary_operator.png" width="300">

## Data flows
Blocks communicate through two flows:
- main flow - defines the execution order;
- variable flow - passes values between blocks.

## Debugger
Breakpoints can be set **only on main flow blocks**. After the program is compiled, you can step through the executed
blocks one by one or continue to the next breakpoint.

<img src="app/src/main/java/com/example/android_blueprint/image/debug_gif.gif" width="300">

## License
[MIT](LICENSE)

## Feedback
To ask a question, suggest a feature or report a bug, create an [issue](https://github.com/rendivy/GOPrint/issues).
Describe your question or suggestion in the "Description" field.
If you report a bug, add the steps to reproduce it.
