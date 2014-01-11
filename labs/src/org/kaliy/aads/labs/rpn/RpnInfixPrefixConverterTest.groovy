package org.kaliy.aads.labs.rpn

class RpnInfixPrefixConverterTest extends GroovyTestCase {

    def converter

    @Override
    void setUp() {
        converter = new RpnInfixPrefixConverter()
    }

    void testConverterReturnsOneNumberOnOneNumberInput() {
        assert "1" == converter.convertToPrefix("1")
    }

    void testConverterIgnoresSpaces() {
        assert "1" == converter.convertToPrefix("    1     ")
    }

    void testConvertersConvertsOperators() {
        assert "1 1 +" == converter.convertToPrefix("1+1")
    }

    void testConverterConvertsOperatorsWithPriority() {
        assert "1 2 3 * +" == converter.convertToPrefix("1 2 3 * +")
    }

    void testConverterConvertsOperatorsWithParentheses() {
        assert "2 3 + 4 *" == converter.convertToPrefix("(2+3)*4")
    }

    void testConverterConvertsOperatorWithMultipleParentheses() {
        assert "1 2 3 + * 5 +" == converter.convertToPrefix("(1*(2+3))+5")
    }

    void testConverterConvertsPowerOperator() {
        assert "1 2 3 ^ +" == converter.convertToPrefix("1+2^3")
    }

    void testConverterConvertOperatorsWithThreeDifferentOperators() {
        assert "1 2 3 4 ^ * +" == converter.convertToPrefix("1+2*3^4")
    }

}
