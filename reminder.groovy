//
// Entry's spec:
//    /DATETIME REGEX PATTERN for yyyy-MM-dd HH:mm:ss/: [message: "YOUR_MESSAGE", target: "TARGET_CHANNEL", type: "NOTICE or PRIVMSG"]
//
[
    /.*/: [message: "Every second!", target: "#test", type: "NOTICE"],
    /:.0$/: [message: "Every 10 secons!", target: "#test", type: "PRIVMSG"],
    /^2014-01-01 00:00:00$/: [message: "Happy, new year!!", target: "#test", type: "NOTICE"],
]
