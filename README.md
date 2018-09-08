# Notifier Bot

### @NotifierTelegramBot
https://telegram.me/NotifierTelegramBot

### Start
Register your chat with command `/start`

### Notification
* `/create` new channel and choose its name
* You will receive token for your channel
* To see all your channels execute `/admin`
* Now you can send notification:
```
curl -XPOST "http://sakhalin.market:33333/channels/<token>/notify" -d "message=Hi!"
```
* Notifications will be received by all subscribers of your channel

### Subscription
* Execute `/subscribe` and then write the name of the channel
* You can see the channels you are subscribed to by executing `/list`
* In order to unsubscribe from channel execute `/unsubscribe` and write the name of the channel
