# Spring boot OAuth 2.0


### Get token using credential

`[POST]localhost:8080/oauth/token?grant_type=password&username={USER_ID}&password={USER_PASSWORD}`

with Http BasicAuth using clinet account/secret

### Get token using refresh token

`localhost:8080/oauth/token?grant_type=refresh_token&refresh_token={REFRESH_TOKEN}`

with Http BasicAuth using clinet account/secret


### Check token information

`localhost:8080/oauth/check_token?token={TOKEN}`

with Http BasicAuth using clinet account/secret

