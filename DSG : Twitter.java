/*
AMZN flipcart msft 3-4

https://leetcode.com/problems/design-twitter/

Design a simplified version of Twitter where users can post tweets, 
follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. 
Your design should support the following methods:

postTweet(userId, tweetId): Compose a new tweet.
getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. 

Each item in the news feed must be posted by users who the user followed or by the user herself. 
Tweets must be ordered from most recent to least recent.
follow(followerId, followeeId): Follower follows a followee.
unfollow(followerId, followeeId): Follower unfollows a followee.
*/
class Twitter {
    class UpdatesFollows{
        Set<Integer> follows = new HashSet<>();
        LinkedList<Tweet> tweets  = new LinkedList<>();
    }
    class Tweet {
        int twt; 
        int timeStamp; 
        Tweet(int t, int time){
            twt = t;
            timeStamp = time;
        }
    }
    
    Map<Integer,UpdatesFollows> map;
    int timeStamp=0;
    public Twitter() {
        map = new HashMap<>();
        timeStamp=0;
    }
    
    public void postTweet(int userId, int tweetId) {
        map.putIfAbsent(userId, new UpdatesFollows());
        map.get(userId).tweets.addFirst(new Tweet(tweetId,timeStamp++));
        if(map.get(userId).tweets.size()>10)    // store 10 tweets per user 
            map.get(userId).tweets.removeLast();
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. **/
    public List<Integer> getNewsFeed(int userId) {
        map.putIfAbsent(userId, new UpdatesFollows());
        // MIN_HEAP bcz we dont want old updates. 
        PriorityQueue<Tweet> pq = new PriorityQueue<Tweet>(new Comparator<Tweet>(){ 
            public int compare(Tweet a ,Tweet b){
                return a.timeStamp - b.timeStamp;
            }
        });
        
        // follow yourself first 
        map.get(userId).follows.add(userId);
        
        for(int follows1 : map.get(userId).follows){
            if(map.containsKey(follows1)){
                for(Tweet t: map.get(follows1).tweets){
                    pq.add(t);
                    if(pq.size()>10)
                        pq.poll();
                }
            }
        }
        
        List<Integer> list = new ArrayList<>();
        while(!pq.isEmpty())
            list.add(0,pq.poll().twt);
        
        return list;
    }

    public void follow(int followerId, int followeeId) {
        map.putIfAbsent(followerId, new UpdatesFollows());
        map.get(followerId).follows.add(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        map.putIfAbsent(followerId, new UpdatesFollows());
        map.get(followerId).follows.remove(followeeId);
    }
}
