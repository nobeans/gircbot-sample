import groovy.json.*

def text = new URL("http://api.openweathermap.org/data/2.5/weather?q=${args.join(',')}").text
def json = new JsonSlurper().parseText(text)
def weather = json?.weather?.description?.first() ?: "unknown"
def iconUrl = "http://openweathermap.org/img/w/${json?.weather?.icon?.first()}.png"

return "$weather $iconUrl"
