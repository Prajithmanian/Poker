var restify = require('restify');

const execFile = require('child_process').execFile;

var server = restify.createServer();
server.use(restify.queryParser());

server.get('/exec', function(req, res, next){
	var args = JSON.parse( req.params.q );
	console.log(args);
	// Insert your code here to execute command and send response instead of hello  world
	execFile('java', ['Poker'].concat(args), (error, stdout, stderr) => {
		if (error) {
			throw error;
		}
			res.send(stdout);
		});

});

server.get('/', restify.serveStatic({
  directory: '.',
  default: 'index.html'
}));

server.listen(8082, function(){
	console.log("Server is listening...");
});
