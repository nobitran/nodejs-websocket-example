const WebSocketServer = require('ws').Server,
  wss = new WebSocketServer({ port: 40510 });

wss.on('connection', function (ws) {
  ws.on('message', function (message) {
    console.log('received: %s', message);
  });

  ws.send(`${new Date()}`, function (err) {
    console.log(err);
  });
});
