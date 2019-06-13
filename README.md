# eyeota-coding-solution


### Your thought process / approach towards solving this problem.
Using Jackson, JSON can be Marshalled to get Java objects for it. With Java objects it is easy to filter `SegmentConfig` objects using `Streams`. But, this aproach loads whole JSON file to memory to classes and objects. Instead, using Jackson `readTree` creating a JSON tree will optimize the speed of lookup by and will optimize the memory consumption.

### What were the options or alternatives you considered?
Initial though was to Marshal whole JSON to Java objects and to filter for `SegmentConfig`. But, dropped the thought as it is not optimized for memory and  speed of lookup. 

### How did you finalise on your approach?
Used `readTree` to find the ParameterObject to optimize the speed of lookup. Started traversing using `JSONParser` to make achieve the partial matching functionality of ParamValue.

### How did you measure that your solution meets the functional or non-functional requirements?
Followed the TDD approach and created the Junit testcases following the given Functional requirements. 
No new data structure is added to the repository to Marshal the JSON data and designed with the combination of `readTree` and `JSONParser` to follow the Non-Functional requirement. 
