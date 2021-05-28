A spring boot Maven application uses in-memory mongo db, build a single REST API endpoint that returns a filtered set of products from the provided data in the jsondata.txt file

GET /search/product
Query Parameter			Description

type					The product type. (String. Can be 'phone' or 'subscription')
min_price				The minimum price in SEK. (Number)
max_price				The maximum price in SEK. (Number)
city					The city in which a store is located. (String)
property				The name of the property. (String. Can be 'color' or 'gb_limit')
property:color			The color of the phone. (String)
property:gb_limit_min 	The minimum GB limit of the subscription. (Number)
property:gb_limit_max 	The maximum GB limit of the subscription. (Number)

The response is a JSON array with the products in a 'data' wrapper. 

Example: GET /search/product?type=subscription&max_price=1000&city=Stockholm
{
	data: [ 
		{
		    type: 'subscription',
		    properties: 'gb_limit:10',
		    price: '704.00',
		    store_address: 'Dana gärdet, Stockholm'
	  	},
	  	{
		    type: 'subscription',
		    properties: 'gb_limit:10',
		    price: '200.00',
		    store_address: 'Octavia gränden, Stockholm'
	  	}
	]
}

We are able to:
- build the application with Maven
- make requests to verify the behavior

