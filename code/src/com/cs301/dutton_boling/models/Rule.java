package com.cs301.dutton_boling.models;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Stewart
 * Date: 10/20/13
 * Time: 8:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class Rule {

    Set<Attribute> anecedents = null;
    Set<Attribute> consequents = null;

    public Rule(Set<Attribute> anecedents, Set<Attribute> consequents) {
        this.anecedents = anecedents;
        this.consequents = consequents;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof  Rule)){
            return false;
        }
        return ((Rule) obj).getAnecedents().equals(anecedents) && ((Rule) obj).getConsequents().equals(consequents);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("if ");
        for(Attribute acecedent : anecedents){
            stringBuilder.append(acecedent.toString());
            stringBuilder.append(" and ");
        }
        stringBuilder.setLength(stringBuilder.length() - 5);
        stringBuilder.append(" then ");
        for(Attribute consequent : consequents){
            stringBuilder.append(consequent.toString());
            stringBuilder.append(" and ");
        }
        stringBuilder.setLength(stringBuilder.length() - 5);
        return stringBuilder.toString();
    }

    /**
     * Returns a hash code value for the object. This method is
     * supported for the benefit of hash tables such as those provided by
     * {@link java.util.HashMap}.
     * <p/>
     * The general contract of {@code hashCode} is:
     * <ul>
     * <li>Whenever it is invoked on the same object more than once during
     * an execution of a Java application, the {@code hashCode} method
     * must consistently return the same integer, provided no information
     * used in {@code equals} comparisons on the object is modified.
     * This integer need not remain consistent from one execution of an
     * application to another execution of the same application.
     * <li>If two objects are equal according to the {@code equals(Object)}
     * method, then calling the {@code hashCode} method on each of
     * the two objects must produce the same integer result.
     * <li>It is <em>not</em> required that if two objects are unequal
     * according to the {@link Object#equals(Object)}
     * method, then calling the {@code hashCode} method on each of the
     * two objects must produce distinct integer results.  However, the
     * programmer should be aware that producing distinct integer results
     * for unequal objects may improve the performance of hash tables.
     * </ul>
     * <p/>
     * As much as is reasonably practical, the hashCode method defined by
     * class {@code Object} does return distinct integers for distinct
     * objects. (This is typically implemented by converting the internal
     * address of the object into an integer, but this implementation
     * technique is not required by the
     * Java<font size="-2"><sup>TM</sup></font> programming language.)
     *
     * @return a hash code value for this object.
     * @see Object#equals(Object)
     * @see System#identityHashCode
     */
    @Override
    public int hashCode() {
        return anecedents.hashCode() & consequents.hashCode();
    }

    public Set<Attribute> getAnecedents() {
        return anecedents;
    }

    public Set<Attribute> getConsequents() {
        return consequents;
    }
}
