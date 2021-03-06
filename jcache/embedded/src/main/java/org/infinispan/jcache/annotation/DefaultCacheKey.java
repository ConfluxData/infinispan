package org.infinispan.jcache.annotation;

import static java.util.Arrays.deepEquals;
import static java.util.Arrays.deepHashCode;
import static java.util.Arrays.deepToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.cache.annotation.GeneratedCacheKey;

import org.infinispan.marshall.protostream.impl.MarshallableUserObject;
import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

/**
 * Default {@link javax.cache.annotation.GeneratedCacheKey} implementation.
 *
 * @author Kevin Pollet &lt;kevin.pollet@serli.com&gt; (C) 2011 SERLI
 * @author Galder Zamarreño
 */
public class DefaultCacheKey implements GeneratedCacheKey {

   private static final long serialVersionUID = 4410523928649671768L;

   private final Object[] parameters;
   private final int hashCode;

   @ProtoField(number = 1, collectionImplementation = ArrayList.class, name = "parameters")
   final transient List<MarshallableUserObject> marshallableParams;

   public DefaultCacheKey(Object[] parameters) {
      this.parameters = parameters;
      this.hashCode = deepHashCode(parameters);
      this.marshallableParams = Arrays.stream(parameters).map(MarshallableUserObject::new).collect(Collectors.toList());
   }

   @ProtoFactory
   DefaultCacheKey(List<MarshallableUserObject> marshallableParams) {
      this.marshallableParams = marshallableParams;
      this.parameters = marshallableParams.stream().map(MarshallableUserObject::get).toArray();
      this.hashCode = deepHashCode(this.parameters);
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      DefaultCacheKey that = (DefaultCacheKey) o;

      return deepEquals(parameters, that.parameters);
   }

   @Override
   public int hashCode() {
      return this.hashCode;
   }

   @Override
   public String toString() {
      return new StringBuilder()
            .append("DefaultCacheKey{")
            .append("parameters=").append(parameters == null ? null : deepToString(parameters))
            .append(", hashCode=").append(hashCode)
            .append('}')
            .toString();
   }
}
