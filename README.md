# Pizza Paradise

This repository contains code for the Pizza Paradise API, an API that supports a pizza store. The API can be viewed live on the
[user interface](https://pizza-paradise.herokuapp.com/swagger-ui.html). 

A front-end service was built using this API, and that 
[Alexa skill](https://www.amazon.com/gp/product/B082HD11RB?pf_rd_p=ab873d20-a0ca-439b-ac45-cd78f07a84d8&pf_rd_r=F1K5QCJNA4V1F3YXQSVS)

may be used on the Amazon Alexa app and Alexa-compatible devices (instructions below).
Information about the skill design is available in the 
[front-end design document](https://pizza-paradise.s3-us-west-2.amazonaws.com/resources/pizzaParadiseFrontEndDesignDoc.pdf).

A summary of the entire project may be viewed on the 
[presentation video](https://www.youtube.com/watch?time_continue=1&v=2L7Dqp3WhCQ&feature=emb_title)


#### Setup instructions
- Create new Heroku app
- Add MongoDB
- Push to Heroku

#### Test instructions
- On CLI: mvn verify (to run automated tests)
- On CLI: mvn jacoco:report (to get detailed coverage reports)

#### Alexa skill instructions (all downloads/sign-ups are free)
- To use on Amazon Alexa app:
    1. Enable the skill on Amazon website or app (search for Pizza Paradise API and click the Enable button)
    2. Download Amazon Alexa app
    3. Login with Amazon account
    4. Invoke skill by saying "Alexa order Pizza Paradise" ("Alexa" is the wake word for Alexa; "order Pizza Paradise" is the invocation for the skill)
- If you own an Alexa-compatible device, enable the skill as you would any other and invoke with "order Pizza Paradise"
- To view source project
    1. Sign up for [Voiceflow](https://www.voiceflow.com/)
    2. Download/import a [copy of the project](https://creator.voiceflow.com/dashboard?import=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwcm9qZWN0SWQiOjczNTYxLCJwcm9qZWN0TmFtZSI6IlBpenphIFBhcmFkaXNlIEFQSSIsImlhdCI6MTU3NTUwNTE4Nn0.nfzP7jzWdev0-mjf6vPVt6NrL9h3ZWvPhB37jjkns-s)
    3. Test the skill in Voice flow or click Upload to Alexa to test in the Alexa Developer console
    
