const WebSocketServer = require('ws').Server,
  wss = new WebSocketServer({ port: 40510 });
console.log('ws://localhost:40510');

wss.on('connection', function (ws) {
  for (let i = 0; i < 1000; i++) {
    console.log('index-core', i);
  }
  ws.on('message', function (message) {
    for (let i = 0; i < 1000; i++) {
      console.log('index ', i);
    }
    console.log('received: %s', message);
  });

  ws.send(`${new Date()}`, function (err) {
    console.log(err);
  });
});
