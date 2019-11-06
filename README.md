# jxls-issue180-poi

This is a POI-only testcase for the POI "bug" discussed in https://bitbucket.org/leonate/jxls/pull-requests/112/issue-180-nested-sums/diff

Updating JXLS from POI 4.0.1 to 4.1.0 we had a problem with change https://github.com/apache/poi/commit/1253a29

In setCellFormula() the unsetting of the v attribute has been removed.

## Eclipse

Clone this Git project. Run target "eclipse" of build.xml and refresh workspace.

## Run testcase

Ensure that POI 4.1.0 is used. Run class Issue180Test. Open target/test_OUTPUT.xlsx with MS Excel. The sum in cell A5 is still 3. That's wrong. The v attribute was not unset and so Excel did no automatic recalculation when opening the file with Excel.

You cannot verify the result using POI. It's not possible to write a full automatic JUnit testcase. The bug appears only if you open the file with Excel.

### Steps to get the right result

- Using POI 4.0.1 the sum has the right value 6.
- Using POI 4.1.0 and uncommenting the unsetV code the sum is also 6. If the v attribute is unset Excel will recalculate the sum when opening the Excel file.
- Using POI 4.1.0 and uncommenting the evaluateAll code will also produce 6.
