var player;
var sourcePlayer;

function onYouTubeIframeAPIReady() {
    var youtubeLink = sampleLink; 
    var videoId = youtubeLink.split('v=')[1];

    player = new YT.Player('player', {
        height: '315',
        width: '460',
        videoId: videoId,
        events: {
            'onReady': onPlayerReady
        }
    });

    var sourceYoutubeLink = sampleSourceLink; 
    var sourceVideoId = sourceYoutubeLink.split('v=')[1];

    sourcePlayer = new YT.Player('sourcePlayer', {
        height: '315',
        width: '460',
        videoId: sourceVideoId,
        events: {
            'onReady': onSourcePlayerReady
        }
    });
}

function onPlayerReady(event) {
    player.setVolume(25);
}

function onSourcePlayerReady(event) {
    sourcePlayer.setVolume(25);
}
