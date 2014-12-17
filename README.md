2.1.	 Parser
This component is responsible for converting a given text file into a Document representation. A Document is nothing but a collection of fields. Each field can have its own indexing strategy that would be applied by the IndexWriter. More details are provided in the following section. 

Each parsed Document must have the following fields except the optional Author related fields. The field values should be preserved as-is from the text file and no textual transformations must be applied within the parsing stage.

S. no.	Field Name	Field Description	Example(s)
a.		FileId (FieldNames.FILEID)	The name of the file, also a unique identifier for the document	0001371
b.		Category (FieldNames.CATEGORY)	The category to which the document belongs. Each file is present within the subdirectory of its corresponding category	alum, coffee, cpi, etc.
c.		Title (FieldNames.TITLE)	The title for the document. This is usually the first line of text in all caps before other metadata like place, date or author are mentioned	U.S. COMMERCE’S ORTNER SAYS YEN UNDERVALUED
d.		Author (FieldNames.AUTHOR)	The name(s) of the author(s) of the Document. This is an optional tag and if available, would be present after the title within the <AUTHOR> tag. The format is “By/by/BY <author names separated by and>[, author org]”	Ajoy Sen, BERNICE NAPACH, etc.
e.		AuthorOrg (FieldNames.AUTHORORG)	The organization to which the author of the Document belongs.  Refer to above on parsing details.	Reuters
f.		Place (FieldNames.PLACE)	The place from where the news is being reported. Usually present as the first word in the news text (after the author tag if present)	WASHINGTON, TOKYO, etc.
g.		NewsDate (FieldNames.NEWSDATE)	The date on which the given news was reported. Usually present after the place and formatted as MMMMM dd 	March 5, April 15 etc.
h.		Content (FieldNames.CONTENT)	The actual news content for the Document. This is the rest of the article with all metadata removed	Commerce Dept. undersecretary of economic affairs….

To reiterate, the Parser is responsible for converting a given file into a Document. Refer to the API documentation in the later sections to find out how to instantiate and use these classes. The Document class is simply a container and has been provided for you. You have to implement the Parser for the given method signatures.  
2.2.	IndexWriter
Once a given file has been converted into a Document, the IndexWriter is responsible for writing the fields to the corresponding indexes and dictionaries. You are expected to implement the following indexes. Representative snapshots of the indexes and dictionaries follow.
•	Term index: An index that maps different terms to documents. This is the standard index on which you would perform retrieval.
•	Author index: An author to document index, stores the different documents written by a given author.
•	Category index: A category to document index, stores the different documents classified by a given category.
•	Place index: A place to document index, stores the different documents as referenced by a given place.

Note that the snapshots are only meant for illustration purposes – you are free to implement them with any data structures you may like.

a.	Document dictionary (Optional): This contains a Document to document id mapping. This dictionary is optional as you may use the FileId as the document id. However, you may be able to achieve some compression benefits by assigning document ids manually. A snapshot of what this dictionary might look like is given below:

File Id	Doc id
…	…
0000524	12
0001210	13
…	

This dictionary if created will be referenced by all the indexes.

b.	Term dictionary: Like above it contains a term to term id mapping as illustrated below
Term	Term id
…	…
apple	12
applicat	13
…	…

c.	Term index: It is a simple inverted index storing the document postings list for each term as follows
1	10,22,65,….
2	17,31,88,…
3	12,16,82,112,….
……	

d.	Author dictionary & author index: Analogous to term dictionary and index; create a dictionary and index for authors. Note that if a document has multiple authors, the mapping must be stored against each author. The author organization if available, must be stored in the author dictionary.
e.	Category dictionary & category index: Similar to term and author indexes, create a category dictionary and index.
f.	Place dictionary & place index: Similar to term, author and category indexes, create a place dictionary and index.

We leave it to the students to decide whether they want to create one large merged index or multiple smaller indexes. However, for each index there should not be more than 27 buckets: one for each character and one more for symbols, special characters and numbers.

You may have observed that some fields (title for example) do not have dedicated indexes.  All of these fields should be added to the term index. However, you are free to use any special handling you may like to index them. One approach for example, is to boost the title field. 
3.	Technical details

This section provides details about the starter code, and corresponding methods that need to be implemented.

Note that you are expected to add appropriate error handling for each TODO method. Our tests will enforce this and would affect your scores.

We have provided two main classes that serve as entry points to the code: Runner and Tester. The former triggers the indexing process and the latter runs all the unit tests.

Runner:
•	It simply takes two inputs: the input directory and the index directory.
•	It iterates over each file within the input directory, passes it to the Parser to convert it into a Document and then calls the addDocument method on IndexWriter.
•	After all files have been processed, it calls the close method
Refer to the IndexWriter section below for more information on the methods.

Tester: TBD

3.1.	Parsing
This stage consists of mainly two classes:
3.1.1.	Parser
This class is responsible for parsing the given file into a Document instance. 

Methods to be implemented:
Document	parse	This is a static method that parses the given file into a fully populated Document instance.  

If any exception occurs, the method should throw a ParseException. 
1.		String filename	This is the name of the news file to be parsed. It is a fully qualified filename 

3.1.2.	Document
This class is simply a container. The Parser must call the setField method with the correct values. The IndexWriter should call getField to read the values. We would test the Parser code by calling the different getField methods and verifying the values returned.
3.2.	Tokenization
This stage is responsible for transforming the given Document class instances into Tokens (technically TokenStream instances but read on). We present the descriptions of all the important classes and the methods that must be implemented.
3.2.1.	Token
This is the smallest logical element that would be indexed. At the very least, each Token would have some text associated with it. The termText field as a string and the termBuffer field as a character array represent this.

You are free to add any other information or metadata that you may like with each token. This for example can include positional information or special tokens that mark sentence boundaries for example.

You are expected to implement the following methods in this class:

String	toString	This should return the pure string representation of the token if and only if it should be indexed. The caveat has been added only in case you add special tokens. We would be using this method to verify tokenization and hence, must be implemented correctly.

void	merge	This method should merge the current token with all provided tokens. You should take care of appropriately merging all data structures that you have created. The toString() method after merging should appropriately return the merged text.  
1.		Token[] tokens	The tokens to be merged. 

3.2.2.	TokenStream
This is an iterable stream of Tokens. Refer to the Iterator javadocs for more details about the inherited abstract methods. Apart from the inherited methods, you must implement the following methods:

void	reset	Calling this method should reset the iterator pointer back to the beginning of the stream such that hasNext() returns true and next() returns the first token.  

void	append	Calling this method would append the given TokenStream to the end of the current token stream. Calling this method should not affect the stream pointer except if it has reached the end of the stream.  

If the argument is null or empty, no changes should occur. 
1.		TokenStream other	The TokenStream to be appended 

3.2.3.	Tokenizer
This class is responsible for converting strings into TokenStream objects. A Tokenizer is instantiated without any arguments or a given delimiter. The former merely implies space-delimited tokenization. Apart from the constructors, you must implement the following method:

TokenStream	consume	This method consumes the given string and converts it into a TokenStream object. The number and nature of Tokens created depends upon the delimiter the Tokenizer has been instantiated with.  

In case of any error, it must throw a TokenizerException
1.		String str	The string to be consumed 

3.2.4.	Analyzer
This is an interface that defines operations over TokenStream objects. Multiple Analyzer instances can be chained to allow successive operations on a given TokenStream. There are two levels of usage – either as a single Analyzer as a TokenFilter instance or as a chained Analyzer applicable for a given FieldName. The nuances are explained in the TokenFilterFactory and AnalyzerFactory sections that follow.

In either mode, the following methods must be implemented and as described below:

void	increment	Calling this method implies all operations on the current Token should be completed and the underlying stream should be moved to the next Token.  The operations to be performed are determined by the constituent Analyzers.

In case of any error, it must throw a TokenizerException

TokenStream	getStream	Calling this method would return the underlying TokenStream. This method should be called after the entire TokenStream has been iterated over after calling the increment method.

3.2.5.	TokenFilter and TokenFilterType
A TokenFilter is a single Analyzer instance denoted by a given TokenFilterType. You must implement one class per type. The type definitions and their expected functionality are as given below:

S.no	TokenFilterType	Description
1.		SYMBOL	It should act on the following symbols with actions as described:
•	Any punctuation marks that possibly mark the end of a sentence (. ! ?) should be removed. Obviously if the symbol appears within a token it should be retained (a.out for example).
•	Any possessive apostrophes should be removed (‘s s’ or just ‘ at the end of a word). Common contractions should be replaced with expanded forms but treated as one token. (e.g. should’ve => should have). All other apostrophes should be removed.
•	If a hyphen occurs within a alphanumeric token it should be retained (B-52, at least one of the two constituents must have a number). If both are alphabetic, it should be replaced with a whitespace and retained as a single token (week-day => week day). Any other hyphens padded by spaces on either or both sides should be removed.
2.		ACCENTS	All accents and diacritics must be removed by folding into the corresponding English characters.
3.		SPECIALCHARS	Any character that is not a alphabet or a number and does not fit the above rules should be removed.
4.		DATES	Any date occurrence should be converted to yyyymmdd format for dates and HH:mm:ss for time stamps (yyyymmdd HH:mm:ss for both combined).  Time zones can be ignored. The following defaults should be used if any field is absent:
•	Year should be set as 1900.
•	Month should be January
•	Date should be 1st.
•	Hour, minute or second should be 00.
5.		NUMBERS	Any number that is not a date should be removed.
6.		CAPITALIZATION	All tokens should be lowercased unless:
•	The whole word is in caps (AIDS etc.)
•	The word is camel cased and is not the first word in a sentence. 
•	If adjacent tokens satisfy the above rule, they should be combined into a single token (San Francisco, Brad Pitt, etc.)
7.		STEMMER	A stemmer that replaces words with their stemmed versions. 
8.		STOPWORDS	A stopword removal rule. It removes tokens that occur in a standard stop list.

All classes you write must use the given constructor. You should hardcode any other information that you may need (a stop word list for example). Although not a great programming practice, it does minimize testing errors on our part.

3.2.6.	TokenFilterFactory 
You must implement this class, i.e. the two given methods as described below:

TokenFilterFactory	getInstance	A static method to get an instance of the factory class. This method is static so as to facilitate a singleton factory instance.

TokenFilter	getFilterByType	This method must return an instance of the class that implements the given TokenFilterType filter. 
1.		TokenFilterType type	The TokenFilterType instance we are interested in. 

We would test your individual TokenFilter implementations by instantiating them via the above method. Ideally, you should instantiate them in the same way while building your Analyzer chains. The singleton nature of the factory would allow you to reuse the same TokenFilter instances in multiple chains if needed.
3.2.7.	AnalyzerFactory
You must implement this class, i.e. the two given methods as described below:

AnalyzerFactory	getInstance	A static method to get an instance of the factory class. This method is static so as to facilitate a singleton factory instance.

Analyzer	getAnalyzerForField	This method must return a chained Analyzer for the given FieldName. Different fields would have different Analyzer chains and may follow different orders.

You are free to implement either one single class that implements the chained behavior and return customized instances based on the provided argument or individual classes for each field name.

We would be using the methods inherited from the Analyzer interface to test your classes. Thus, you are encouraged to use only these methods when calling them in your indexer code.
1.		FieldNames name	The FieldName for which the Analyzer is requested. We would call this with one of: TERM, PLACE, AUTHOR or CATEGORY. 

3.3.	Indexing
We provide only a single class as an entry point to the Indexing process, namely the IndexWriter. There are only two methods in this class that serve as entry points, addDocument that adds a document and close that indicates completion of all documents being added. In case of any error, you should throw an IndexerException.

You are expected to implement these methods and add classes as needed. It is expected that you use the AnalyzerFactory and TokenFilterFactory classes while implementing these methods. 

Note that your program must NOT write any files outside of the indexing directory provided as an argument to the writer. Make no assumptions about the directory from where your program is invoked. You WILL lose points if your program writes any files outside the given directory even though they may be temporary.
3.4.	Index reader
This is the final component. All this class does is give utility methods to read an index (which means all including referenced dictionaries). The different methods are listed below:
•	Constructor: It takes two arguments: the index directory and the field on which the index was constructed.
•	getTotalKeyTerms and getTotalValueTerms: This is just the size of the underlying dictionaries, the former for the key field and latter is for the value field.
•	getPostings: Method to retrieve the postings list for a given term. Apart from the corresponding reverse lookups (for both keys and values), the expected result is only a map with the value field as the key of the map and the number of occurrences as the value of the map.
•	getTopK: This returns the key dictionary terms that have the k largest postings list. The return type is expected to be an ordered string in the descending order of result postings, i.e., largest in the first position, and so on.
•	query: This emulates an evaluation of a multiterm Boolean AND query on the index. Implement this only for the bonus. The order of the entries in the map is again defined by the cumulative sum of the number of occurrences. What that means is, when evaluating the queries, for every retained postings entry, add its local occurrences to its running count. The value with the maximum occurrences should be at the top. 
