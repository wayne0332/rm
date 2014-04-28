package cn.jason.rm.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO
 *
 * @author linjiangsheng
 * @created 14-4-18
 */
public class TestJava8Performance
{

	private int iterCount = 5000;

	public void testBuildCollection()
	{
		recordTime(() -> {
			for (int i = 0; i < iterCount; i++)
			{
				Stream.iterate(1, seed -> seed + 1).limit(iterCount).collect(Collectors.toList());
			}
		});

		recordTime(() -> {
			for (int i = 0; i < iterCount; i++)
			{
				Stream.iterate(1, seed -> seed + 1).limit(iterCount).parallel().collect(Collectors.toList());
			}
		});

		recordTime(() -> {
			for (int i = 0; i < iterCount; i++)
			{
				List<Integer> list = new ArrayList<Integer>();
				for (int j = 0; j < iterCount; j++)
				{
					list.add(j);
				}
			}
		});
	}

	@Test
	public void testIter()
	{
		List<Integer> list = Stream.iterate(1, seed -> seed + 1).limit(iterCount * iterCount).collect(Collectors.toList());
		recordTime(() -> {
			for (Integer integer : list)
			{
				if (true)
				{
				}
			}
		});

		recordTime(() -> list.forEach(o -> {
			if (true)
			{
			}
		}));
	}

	public interface RecordTime
	{
		public void doThing();
	}

	public void recordTime(RecordTime recordTime)
	{
		long starTime = System.currentTimeMillis();
		recordTime.doThing();
		System.out.println("用时:" + String.valueOf(System.currentTimeMillis() - starTime));
	}
}
