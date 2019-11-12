currentItem = prj;
currentItemStack.push(prj);

// SCRIPT
currentItemStack.push(currentItem);
function identStr(str, ident, prefix) {
    var result = "";
    var strs = str.split("\n");
    var len = strs.length;
    for (var i=0; i<len; i++) {
        for (var j=0; j<ident; j++)
            result += " ";
        result += prefix;
        result += strs[i];
        if (i != len-1)
             result += "\n";
    }
    return result;
}


currentItem = currentItemStack.pop();
// END OF SCRIPT

// TEXT
print("\n");

// REPEAT
currentItemStack.push(currentItem);

try {
    eval('var rootElem = currentItem');
}catch (ex) {
    log('template.cot(19):<@REPEAT@> Error exists in  path expression.');
    throw ex
}
try {
    eval('var elemArr1 = getAllElements(true, rootElem, \'UMLClass\', \'\', \'\')');
}catch (ex) {
    log('template.cot(19):<@REPEAT@> Error exists in path, type, collection name.');
    throw ex
}
try {
    for (var i1 = 0, c1 = elemArr1.length; i1 < c1; i1++ ) {
        currentItem = elemArr1[i1];
        
        // TEXT
        print("");
        
        // SCRIPT
        currentItemStack.push(currentItem);
        
    fileBegin(getTarget()+"\\"+current().Name+".java");


        currentItem = currentItemStack.pop();
        // END OF SCRIPT
        
        // TEXT
        print("/**\n");
print(identStr(current().Documentation, 0, " * "));        
        // TEXT
        print("\n */\npublic class ");
        
        // DISPLAY
        currentItemStack.push(currentItem);
        try {
            eval('currentItem = currentItem');
        } catch (ex) {
            log('template.cot(27):<@DISPLAY@> Error exists in path expression.');
            throw ex;
        }
        var value;
        try {
            eval('value = current().Name');
        } catch (ex) {
            log('template.cot(27):<@DISPLAY@> Error exists in arguments.');
            throw ex;
        }
        try {
           print(value);
        } catch (ex) {
            log('template.cot(27):<@DISPLAY@> Error exists in command.');
            throw ex;
        }
        currentItem = currentItemStack.pop();
        // END OF DISPLAY
        
        // TEXT
        print(" {\n\n");
        
        // REPEAT
        currentItemStack.push(currentItem);
        
        try {
            eval('var rootElem = currentItem');
        }catch (ex) {
            log('template.cot(29):<@REPEAT@> Error exists in  path expression.');
            throw ex
        }
        try {
            eval('var elemArr2 = getAllElements(false, rootElem, \'UMLAttribute\', \'\', \'\')');
        }catch (ex) {
            log('template.cot(29):<@REPEAT@> Error exists in path, type, collection name.');
            throw ex
        }
        try {
            for (var i2 = 0, c2 = elemArr2.length; i2 < c2; i2++ ) {
                currentItem = elemArr2[i2];
                
                // TEXT
                print("    ");
                
                // DISPLAY
                currentItemStack.push(currentItem);
                try {
                    eval('currentItem = currentItem');
                } catch (ex) {
                    log('template.cot(30):<@DISPLAY@> Error exists in path expression.');
                    throw ex;
                }
                var value;
                try {
                    eval('value = current().TypeExpression');
                } catch (ex) {
                    log('template.cot(30):<@DISPLAY@> Error exists in arguments.');
                    throw ex;
                }
                try {
                   print(value);
                } catch (ex) {
                    log('template.cot(30):<@DISPLAY@> Error exists in command.');
                    throw ex;
                }
                currentItem = currentItemStack.pop();
                // END OF DISPLAY
                
                // TEXT
                print(" ");
                
                // DISPLAY
                currentItemStack.push(currentItem);
                try {
                    eval('currentItem = currentItem');
                } catch (ex) {
                    log('template.cot(30):<@DISPLAY@> Error exists in path expression.');
                    throw ex;
                }
                var value;
                try {
                    eval('value = current().Name');
                } catch (ex) {
                    log('template.cot(30):<@DISPLAY@> Error exists in arguments.');
                    throw ex;
                }
                try {
                   print(value);
                } catch (ex) {
                    log('template.cot(30):<@DISPLAY@> Error exists in command.');
                    throw ex;
                }
                currentItem = currentItemStack.pop();
                // END OF DISPLAY
                
                // TEXT
                print(";\n");
        
            }
        } catch (ex) {
            log('template.cot(30):<@REPEAT@> Error exists in command.');
            throw ex;
        }
        currentItem = currentItemStack.pop();
        // END OF REPEAT
        
        // TEXT
        print("");
        
        // REPEAT
        currentItemStack.push(currentItem);
        
        try {
            eval('var rootElem = currentItem');
        }catch (ex) {
            log('template.cot(32):<@REPEAT@> Error exists in  path expression.');
            throw ex
        }
        try {
            eval('var elemArr3 = getAllElements(false, rootElem, \'UMLOperation\', \'\', \'\')');
        }catch (ex) {
            log('template.cot(32):<@REPEAT@> Error exists in path, type, collection name.');
            throw ex
        }
        try {
            for (var i3 = 0, c3 = elemArr3.length; i3 < c3; i3++ ) {
                currentItem = elemArr3[i3];
                
                // TEXT
                print("\n");
                
                // SCRIPT
                currentItemStack.push(currentItem);
                 typeStr = ""; argStrs = ""; argStr = ""
                currentItem = currentItemStack.pop();
                // END OF SCRIPT
                
                // TEXT
                print("");
                
                // REPEAT
                currentItemStack.push(currentItem);
                
                try {
                    eval('var rootElem = currentItem');
                }catch (ex) {
                    log('template.cot(35):<@REPEAT@> Error exists in  path expression.');
                    throw ex
                }
                try {
                    eval('var elemArr4 = getAllElements(false, rootElem, \'\', \'Parameters\', \'current().DirectionKind==3\')');
                }catch (ex) {
                    log('template.cot(35):<@REPEAT@> Error exists in path, type, collection name.');
                    throw ex
                }
                try {
                    for (var i4 = 0, c4 = elemArr4.length; i4 < c4; i4++ ) {
                        currentItem = elemArr4[i4];
                        
                        // TEXT
                        print("");
                        
                        // SCRIPT
                        currentItemStack.push(currentItem);
                         typeStr = current().TypeExpression; 
                        currentItem = currentItemStack.pop();
                        // END OF SCRIPT
                        
                        // TEXT
                        print("");
                
                    }
                } catch (ex) {
                    log('template.cot(35):<@REPEAT@> Error exists in command.');
                    throw ex;
                }
                currentItem = currentItemStack.pop();
                // END OF REPEAT
                
                // TEXT
                print("");
                
                // REPEAT
                currentItemStack.push(currentItem);
                
                try {
                    eval('var rootElem = currentItem');
                }catch (ex) {
                    log('template.cot(38):<@REPEAT@> Error exists in  path expression.');
                    throw ex
                }
                try {
                    eval('var elemArr5 = getAllElements(false, rootElem, \'\', \'Parameters\', \'current().DirectionKind!=3\')');
                }catch (ex) {
                    log('template.cot(38):<@REPEAT@> Error exists in path, type, collection name.');
                    throw ex
                }
                try {
                    for (var i5 = 0, c5 = elemArr5.length; i5 < c5; i5++ ) {
                        currentItem = elemArr5[i5];
                        
                        // TEXT
                        print("");
                        
                        // SCRIPT
                        currentItemStack.push(currentItem);
                        
argStr = current().TypeExpression + " " + current().Name;
argStrs += argStr;

                        currentItem = currentItemStack.pop();
                        // END OF SCRIPT
                        
                        // TEXT
                        print("");
                
                    }
                } catch (ex) {
                    log('template.cot(38):<@REPEAT@> Error exists in command.');
                    throw ex;
                }
                currentItem = currentItemStack.pop();
                // END OF REPEAT
                
                // TEXT
                print("    /**\n    ");
print(identStr(current().Documentation, 0, " * "));                
                // TEXT
                print("\n     */\n    ");
print(typeStr);                
                // TEXT
                print(" ");
print(current().Name);                
                // TEXT
                print("(");
print(argStrs);                
                // TEXT
                print(") {\n        // implement here...\n    }\n");
        
            }
        } catch (ex) {
            log('template.cot(35):<@REPEAT@> Error exists in command.');
            throw ex;
        }
        currentItem = currentItemStack.pop();
        // END OF REPEAT
        
        // TEXT
        print("}\n");
        
        // SCRIPT
        currentItemStack.push(currentItem);
        
    fileEnd();

        currentItem = currentItemStack.pop();
        // END OF SCRIPT
        
        // TEXT
        print("");

    }
} catch (ex) {
    log('template.cot(35):<@REPEAT@> Error exists in command.');
    throw ex;
}
currentItem = currentItemStack.pop();
// END OF REPEAT

// TEXT
print("\n");

// TEXT
print("");

// TEXT
print("");

// TEXT
print("");

// TEXT
print("");

// TEXT
print("");

// TEXT
print("");
