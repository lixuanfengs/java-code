package com.sxt.sso.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;
import org.springframework.core.convert.converter.Converter;

import com.sxt.sso.controller.Employee;

public class Lambda1 {
	
	
	static int outerStatticNum;
	int outerNum;
	public static void main(String[] args) {
		System.out.println(Lambda1.class.getClassLoader());
	}
	
	//访问字段和静态变量
	//@Test
	public void testScopes() {
		
		Converter<Integer, String> StringConverter =  (from) -> {
			outerNum = 23;
			return String.valueOf(from);
		};
		System.out.println(StringConverter.convert(5));
		System.out.println(outerNum);
		
		Converter<Integer, String> stringConverter2 = (from) -> {
			outerStatticNum = 72;
            return String.valueOf(from);
        };
        System.out.println(stringConverter2.convert(5));
		System.out.println(outerStatticNum);
	}
	
	//谓词  Predicate<T>: 谓词是一个参数的布尔值函数。该接口包含各种默认方法，用于将谓词组合成复杂的逻辑术语（和，或，否定）
	//@Test
	public void testScopes2() {
		Predicate<String> predicate = (s) -> s.contains("h");
		if(predicate.test("seyse")) {
			System.out.println("包含");
		}else {
			System.out.println("不包含");
		}
		//System.out.println(predicate.test("sssss"));
		
		if(predicate.negate().test("sadpas")) {
			System.out.println("negate-- 包含");
		}else {
			System.out.println("negate-- 不包含");
		}
		System.out.println("=================================================");
		Boolean s = false;
		Predicate<Boolean> nonNull = Objects::nonNull;
		System.out.println(nonNull.test(s));
		
		Boolean s1 = false;
		Predicate<Boolean> isNull = Objects::isNull;
		System.out.println(isNull.test(s1 ));
		
		Predicate<String> isEmpty = String::isEmpty;
		Predicate<String> isNotEmpty = isEmpty.negate();
		System.out.println(isNotEmpty.test(""));
		System.out.println(isEmpty.test(""));
	}
	
	//功能 Function<T,R> :函数接受一个参数并生成结果。默认方法可用于将多个函数链接在一起（compose，andThen）。
	//@Test
	public void testScopes3() {
		Function<String, Integer> toInteger = Integer::valueOf;
		Function<String, Double> toString = Double::valueOf;
		System.out.println(toString.apply("2.1"));
		System.out.println(toInteger.apply("489"));
	}
	//供应商 Supplier<T> :供应商生成给定通用类型的结果。与功能不同，供应商不接受参数。
	//@Test
	public void testScopes4() {
		Supplier<Employee> employee = Employee::new;
		System.out.println(employee.get());
	}
	//消费者 Consumer<T> :消费者表示要对单个输入参数执行的操作。
	//@Test
	public void testScopes5() {
		Consumer<Employee> greeter = (p) -> System.out.println("Hello :"+ p.getName());
		greeter.accept(new Employee("李四",19,9998.0));
	}
	
	//比较  Comparator<T> :比较器在Java的旧版本中是众所周知的。Java 8为接口添加了各种默认方法。
	//@Test
	public void testScopes6() {
		//判断两个employee 的age 是否相等   0相等  -1不相等
		Comparator<Employee> comparator = (p1,p2) -> p1.getAge().compareTo(p2.getAge());
		Employee e1 = new Employee("李四",19,9999.0);
		Employee e2 = new Employee("李s",129,9999.0);
		int s = comparator.compare(e1, e2);
		System.out.println(s);
		
		int s2 = comparator.reversed().compare(e1, e2);
		System.out.println(s2);
	}
	//选配 Optional<T> :可选项不是功能接口，而是防止它的一个很好的实用程序NullPointerException。这是下一节的一个重要概念，让我们快速了解一下Optionals的工作原理。

	//可选是一个值的简单容器，可以为null或非null。想一个可能返回非null结果但有时不返回任何内容的方法。而不是返回null你Optional在Java 8中返回。
	//@Test
	public void testScopes7() {
		Optional<String> optional = Optional.of("abc");
		
		System.out.println(optional.get());
		System.out.println(optional.isPresent());
		System.out.println(optional.orElse("fallback"));
		System.out.println(optional.orElseGet(null));
		optional.ifPresent((s) -> System.out.println(s.charAt(0)));
	}
	//流
	//A java.util.Stream表示可以在其上执行一个或多个操作的元素序列。
	//流操作是中间操作或终端操作。当终端操作返回某种类型的结果时，中间操作会返回流本身，因此您可以连续链接多个方法调用。
	//流在源上创建，例如java.util.Collection类似的列表或集（不支持映射）。
	//流操作可以顺序执行或并行执行。
	//java 8中的集合已扩展，因此您只需通过调用Collection.stream()或创建流即可Collection.parallelStream()。以下部分介绍了最常见的流操作。
	//@Test
	public void testScopes8() {
		List<String> stringCollection = new ArrayList<>();
		stringCollection.add("ddd2");
		stringCollection.add("aaa2");
		stringCollection.add("bbb1");
		stringCollection.add("aaa1");
		stringCollection.add("bbb3");
		stringCollection.add("ccc");
		stringCollection.add("bbb2");
		stringCollection.add("ddd1");
		
		//过滤
		//过滤器接受谓词以过滤流的所有元素。此操作是中间操作，它使我们能够forEach在结果上调用另一个流操作（）。
		//ForEach接受为过滤流中的每个元素执行的使用者。ForEach是一个终端操作。它是void，所以我们不能调用另一个流操作。
		stringCollection
				.stream()
				.filter(s -> s.startsWith("d"))
				.forEach(System.out::println);
		System.out.println("=======================================");
		//排序 Sorted
		//Sorted是一个中间操作，它返回流的排序视图。除非您传递自定义，否则元素按自然顺序排序Comparator
		stringCollection
			.stream()
			.sorted()//可传入自定义排序
			.filter((s) -> s.startsWith("a"))
			.forEach(System.out::println);
		//自定义排序
		Comparator<String> str = (c1,c2) -> c2.compareTo(c1) ;
		stringCollection
			.stream()
			.sorted(str)
			.filter((s) -> s.startsWith("a"))
			.forEach(System.out::println);
		//请记住，sorted这只会创建流的排序视图，而不会操纵已备份集合的顺序。顺序stringCollection是不受影响的：
		System.out.println("========="+stringCollection);
		//地图 map
		//的中间操作map的每个元素转换成经由给定功能的另一个对象。以下示例将每个字符串转换为大写字符串。但您也可以使用map将每个对象转换为另一种类型。结果流的泛型类型取决于传递给函数的泛型类型map。
		stringCollection
			.stream()
			.map(String::toUpperCase)
			.sorted((a,b) -> b.compareTo(a))
			.forEach(System.out::println);
		System.out.println("===============================================================");
		//比赛 Match
		//可以使用各种匹配操作来检查某个谓词是否与流匹配。所有这些操作都是终端并返回一个布尔结果。
		boolean anyMatchStartWithA = stringCollection
						.stream()
						.anyMatch(a -> a.startsWith("h"));
		System.out.println(anyMatchStartWithA);
		System.out.println("===============================================================");
		//计数Count
		//Count是一个终端操作，它将流中的元素数作为a返回long。
		long startsWithB = stringCollection
						.stream()
						//.filter((e) -> e.startsWith("a"))
						.filter(s -> s.endsWith("1"))
						.count();
		System.out.println(startsWithB);
		System.out.println("===============================================================");
		//降低 reduce
		//该终端操作使用给定功能执行流的元素的减少。结果是Optional持有减少的价值。
		Optional<String> reduced = stringCollection
							.stream()
							.sorted()
							.reduce((s1,s2) -> s1 +"_" + s2);
		System.out.println(reduced.get());
	}
	//并行流 Parallel Streams
	//如上所述，流可以是顺序的或并行的。对顺序流的操作在单个线程上执行，而并行流上的操作在多个线程上并发执行。
	//以下示例演示了使用并行流来提高性能的难易程度。
	//首先，我们创建一个大的独特元素列表：
	//@Test
	public void testScopes9() {
		int max = 1000000;
		List<String> values = new ArrayList<>();
		for (int i = 0; i < max; i++) {
			UUID uuid = UUID.randomUUID();
			values.add(uuid.toString());
		}
		//现在我们测量对此集合的流进行排序所需的时间。
		//Sequential Sort
		long t0 = System.nanoTime();
		long count = values.stream().sorted().count();
		System.out.println(count);
		long t1 = System.nanoTime();
		long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		System.out.println(String.format("sequential sort took: %d ms", millis));
		
		//Parallel Sort
		long t01s = System.nanoTime();
		long count1 = values.parallelStream().sorted().count();
		System.out.println(count1);
		long t11s = System.nanoTime();
		long millis1 = TimeUnit.NANOSECONDS.toMillis(t11s - t01s);
		System.out.println(String.format("parallel sort took: %d ms", millis1));
		//正如您所看到的，两个代码片段几乎完全相同，但并行排序大约快了50％。你所要做的就是换stream()到parallelStream()。
	}
	//地图 map
	//如前所述，地图不支持流。相反，地图现在支持各种新的和有用的方法来执行常见任务。
	@Test
	public void testScopes10() {
		Map<Integer, String> map = new HashMap<>();
		
		for (int i = 0; i < 10; i++) {
			map.putIfAbsent(i, "val"+i);
		}
		map.forEach((key, value) -> System.out.println(value));
		
		//上面的代码应该是自我解释的：putIfAbsent阻止我们在null检查时写入额外的代码; 
		//forEach接受使用者对地图的每个值执行操作。
		//此示例显示如何使用函数在地图上计算代码：
		map.computeIfPresent(3, (num, val) -> num + val);
		System.out.println(map.get(3));
		
		
		map.computeIfPresent(3, (num, val) -> val + num);
		System.out.println(map.get(3));             // val33

		map.computeIfPresent(9, (num, val) -> null);
		System.out.println(map.containsKey(9));    // false

		map.computeIfAbsent(23, num -> "val" + num);
		System.out.println(map.containsKey(23));    // true

		map.computeIfAbsent(3, num -> "bam");
		System.out.println(map.get(3));             // val33
		
		//接下来，我们将学习如何删除给定键的条目，前提是它当前映射到给定值：
		map.remove(3, "val3");
		map.get(3);             // val33
		map.remove(3, "val33");
		map.get(3);             // null
		//另一种有用的方法
		map.getOrDefault(42, "not found");  // not found
		//合并地图的条目非常简单：
		map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
		map.get(9);             // val9

		map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
		map.get(9);             // val9concat
	}
	
}
