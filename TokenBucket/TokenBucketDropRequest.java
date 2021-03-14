/*
In this ratelimiting Token Bucket algorithm.
In this if the request is not allowed then request is just droped we dont put that on any waiting. 
*/
class TokenBucket{
    long maxTokens;
    double refillRatePerNanoSec; 

    long lastTimeStamp;
    double currentTokens;


    TokenBucket(long maxTokens, long refillRate /*per second*/){
        this.maxTokens = maxTokens;
        this.refillRatePerNanoSec = refillRate/1e9; // divided by 1,000,000,000;

        currentTokens = maxTokens;
        lastTimeStamp = System.nanoTime(); // remember this method

    }

    public synchronized boolean allowRequest(long tokensRequired){    // synchronized as several thread will call this.
        refillTokens();
        if(tokensRequired < currentTokens){
            currentTokens = currentTokens - tokensRequired;
            return true;
        }
        return false;   // we are just dropping the request 
    }

    private void refillTokens() {     // this method is locked by synchronized block in allowRequest()
        long now = System.nanoTime();
        long diff = now - lastTimeStamp;
        double tokendToAdd = diff * refillRatePerNanoSec;

        currentTokens = Math.min( currentTokens + tokendToAdd, maxTokens);
        lastTimeStamp = now;
    }
}
