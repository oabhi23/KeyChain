# KeyChain

A blockchain inspired service to encrypt and decrypt password data. 

## What if blockchain was introduced to the shared password manager model?

For my CS 5594: Blockchain Technologies research project, I decided to investigate
an alternative to the password manager model.

Using blockchain, we can store and update transactions of password data. For added security,
it would be better to encrypt our data before it is stored on the chain. In this project, 
we simulate the idea of storing sensitive data on a simple blockchain
developed in Java. 

## Implementation

### Simulating a blockchain
To implement a simple blockchain, we will need a simple Block to store our data.
Our Block contains its hash, the previous block's hash, data, timestamp, and nonce. 
We will explain each field below:
1. Hash
   1. Generated from Block#calculateBlockHash, a hash is an output that maps any data
      within the block to an arbitrary size. The hash should be difficult to reverse
      engineer. If somehow, the same block were to be fed through the calculateBlockHash
      function, its hash will be identical. This is useful to validate the data is the same.
2. Previous block's hash
   1. Hash of the previous block which is crucial to build the chain.
3. Data
   1. Transaction data. Data blocks are generally stored within Markle trees in a 
      real blockchain. In our simulation, we use a String to store the encrypted values
      of password data.
4. Timestamp
   1. Time the block was created
5. Nonce
   1. An arbitrary number used in cryptography and in our #mineBlock function.

#### Mining the block

Within the blockchain, miners are conducting the work of verifying the legitimacy
of blockchain transactions. Double spending is one scenario that miners
verify within Bitcoin transactions. In this function, we keep it simple and 
straightforward with the mine function looking to find a hash starting with a 
prefix of zeroes. We increment the nonce and calculate the hash until we find
a hash with X number of consecutive zeroes.

The implementation of the simple blockchain was pulled from Baeldung. The author, Kumar
Chandrakant, aims to implement an application in Java that focuses on Blockchain
concepts.

## My Implementation

Adding to the above, my implementation focuses on encrypting and decrypting the String data stored
within the block. Prior to adding the block on the ArrayList chain, I tokenize the data 
within my TokenizeUtils class. We will discuss each method below, followed by
an explanation of BlockImplementation class.

1. Tokenize Data
   1. Encrypts plain text password value using a local public key. If public key
      does not exist, one is generated and sent to the encryption scheme.
2. Detokenize Data
   1. Decrypts tokenized value to return original plaintext. Here we assume
      a public key exists via an initial tokenize data call. We alert the user if the key does
      not exist or the value cannot be found in the chain.
3. Generate Key
   1. Generates a SecretKey using the AES Encryption algorithm which is a
      symmetric block cipher algorithm with a block size of 128 bits. 
4. Store Key
   1. The key is written locally to a file "suepk."
5. Retrieve Key
   1. Check for an existing SecretKey so that plain text can appropriately
      be retrieved. If a new or different key is used to decrypt a token, 
      the scheme should fail.

### BlockImplementation

BlockImplementation makes use of the methods described above 
and the Scanner class to get user input and tokenize/detokenize password
data. The user is presented 3 options: tokenize, detokenize, and exit. 
Blocks can be added and data retrieved on the Scanner's run.






