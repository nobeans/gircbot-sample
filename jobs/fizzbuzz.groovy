def formattedTime = context.time.format("yyyy-MM-dd HH:mm:ss") 
if (context.time.seconds % 15 == 0) {
    ircControl.sendNotice("#test", "FiZZBUZZ < $formattedTime")
}
else if (context.time.seconds % 3 == 0) {
    ircControl.sendNotice("#test", "FiZZ < $formattedTime")
}
else if (context.time.seconds % 5 == 0) {
    ircControl.sendNotice("#test", "BUZZ < $formattedTime")
}
