# MACA Java framework

## How to run test

Simple run `mvn test`

Tagged run `mvn test -Dcucumber.options="--tags @smoke"`

Run on specified browser (default is Chrome) `mvn test -Dbrowser=ie`

## Page objects

There are only 3 methods to define any page you want.
All methods should call in corresponding constructors

1. defineElement() - define single element

        public class HomePage extends BasePage {
        
            public HomePage() {
                defineElement("Merger Agreement Link", "css", "li > a[href=\"/Search?searchTerm=Merger%20Agreement\"]");
            }
        
        }
2. defineCollection() - define collection of elements

        public class HomePage extends BasePage {
        
            public SearchResultPage() {
                defineCollection("SearchResults", "css", ".result");
            }
        
        }
3. assignComponent() - assign defined page

        public class Footer extends BasePage {
        
            public Footer() {
                defineElement("Terms of Use Link", "css", "a[href=\"http://researchhelp.cch.com/Terms/License_Agreement.html\"]");
                defineElement("Copyright", "css", "div.footerCopyright");
            }
        
        }
        
        public HomePage() {
            assignComponent(new Footer());            
        }