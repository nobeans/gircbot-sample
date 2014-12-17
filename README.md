Gircbot Sample
==============

A simple sample script of [Gircbot](https://github.com/nobeans/gircbot).


Usage
-----

```
$ groovy mybot.groovy
```

Dictionary
----------

You can define a static response for a specific keyword in `dictionary.properties`.

```
#
# Entry's spec:
#     /REGEX_PATTERN/ = MESSAGE
#         The REGEX_PATTERN matches a whole message. It's not required a bot name.
#         A special character must be escaped to use a normal character.
#
hi=Hi!
\\?help=I'm gircbot.
```

Reminder
--------

You can define a job which send a message at a specific date and time in `reminder.groovy`.

```
//
// Entry's spec:
//    /DATETIME REGEX PATTERN for yyyy-MM-dd HH:mm:ss/: [message: "YOUR_MESSAGE", target: "TARGET_CHANNEL", type: "NOTICE or PRIVMSG"]
//
[
/.*/: [message: "Every second!", target: "#test", type: "NOTICE"],
/:.0$/: [message: "Every 10 secons!", target: "#test", type: "PRIVMSG"],
/^2014-01-01 00:00:00$/: [message: "Happy, new year!!", target: "#test", type: "NOTICE"],
]
```




External Command
----------------

You can add a script as `Reactor` into a `command` directory as you want.
It's the easiest way that you can do anything toward a IRC message as you want.
And the edit of source code is immediately applied dynamically.

The following binding variables are available in the script:

Type        | Variable   | Description
------------|------------|-------------
[IrcControl](https://github.com/nobeans/gircbot/blob/master/src/main/java/org/jggug/kobo/gircbot/core/IrcControl.java)  | ircControl | You can control a bot instance e.g. to send a message
Map         | context    | It has the entries which are arguments of the [Reactor](https://github.com/nobeans/gircbot/blob/master/src/main/java/org/jggug/kobo/gircbot/core/Reactor.java)'s method. Their keys are: `channel`, `sender`, `login`, `hostname` and `message`.
List<String>| args       | The arguments from IRC message

The last evaluated value or the returned value is sent as a notify message:

```
// In command/hello.groovy
return "Hello, ${context.sender}!"
```

If the value is null, nothing is sent:

```
// Yet another implementation
ircControl.sendNotify(context.channel, "Hello, ${context.sender}!")
return null
```

You can run this command when you send a message on IRC like "gircbot hello" with your bot name.
