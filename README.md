# Account API

#### Build the application

```sh
./gradlew clean build
```

#### Test units of the application

```sh
./gradlew test
```

#### Run all applications test

```sh
make all-tests
```
`_make_`: You will need to install the make on your machine 

#### Run the application

```sh
./gradlew clean run
```

#### Create an account
##### Endpoint: 
##### POST - http://localhost:7000/accounts
##### Payload: 

```json
{
	"balance": 100.05,
	"id": "2"
}
```

`_Id_`: The users identifier.

_`Balance`_: The users initial balance.

##### Example
```sh
curl -X POST \
  http://localhost:7000/accounts \
  -H 'Content-Type: application/json' \
  -d '{
	"balance": 100.05,
	"id": "2"
}'
```

### Get the list of existent accounts

#### Endpoint: 
##### GET - http://localhost:7000/accounts
##### Example
```sh
curl -X GET \
  http://localhost:7000/accounts
```

### Transfer money between accounts

#### Endpoint: 
##### POST - http://localhost:7000/:giverId/transfers
##### Payload: 

```json
{
	"value": 70.00,
	"beneficiaryId": "2"
}
```

`_BeneficiaryId_`: The identifier of the user that will receive the money. 

_`Value`_: The transactions value to be transferred.

##### Example:
```sh
curl -X POST \
  http://localhost:7000/accounts/13/transfers \
  -H 'Content-Type: application/json' \
  -d '{
	"value": 70.00,
	"beneficiaryId": "2"
}'
```
