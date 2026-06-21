/**
 * Best trade problem
 * Given that you have the daily prices of gold for each day in a given time period,
 * find the best day to buy and sell gold such that profit is maximized
 */
package com.complex_problems.best_trade_problem;

public class BestTradeProblem {
    public static void main(String[] args) {
        int[] prices = {27, 53, 7, 25, 33, 2, 32, 47, 43};
        System.out.println("Brute force approach");
        int[] salesInfo = bestDayToBuyAndSell(prices);
        System.out.println("Best day to buy is day " + salesInfo[0] + " and best day to sell is day " + salesInfo[1]);

        System.out.println("Better approach");
        salesInfo = bestDayToBuyAndSellBottomUpApproach(prices);
        System.out.println("Best day to buy is day " + salesInfo[0] + " and best day to sell is day " + salesInfo[1]);

        System.out.println("Kadane's algorithm approach");
        salesInfo = bestDayToBuyAndSellKA(prices);
        System.out.println("Best day to buy is day " + salesInfo[0] + " and best day to sell is day " + salesInfo[1]);
    }

    // Brute force approach. Calculate all possible sales and find the best profit
    // Time complexity is O(n^2)
    // Space complexity is O(1)
    public static int[] bestDayToBuyAndSell(int[] prices) {
        int best_profit = Integer.MIN_VALUE;
        int best_buy_day = 0;
        int best_sell_day = 0;
        int profit;
        for (int buy_day = 0; buy_day < prices.length; buy_day++) {
            for (int sell_day = buy_day; sell_day < prices.length; sell_day++) {
                profit = prices[sell_day] - prices[buy_day];
                if (profit > best_profit) {
                    best_profit = profit;
                    best_buy_day = buy_day;
                    best_sell_day = sell_day;
                }
            }
        }
        best_buy_day++;
        best_sell_day++;
        return new int[]{best_buy_day, best_sell_day};
    }

    // The best day to buy to sell on day 1 is day 1. B(1) = 1.
    // The best day to buy to sell on day 2 is either day 1 or day 2. B(2) = 1 if price on day 2 is > price on day 1 else B(2) = 2
    // In general, B(day) = day if price(day) < price(B(day-1)) else B(day) = B(day-1)
    // Time complexity O(n)
    // Space complexity O(n)
    public static int[] bestDayToBuyAndSellBottomUpApproach(int[] prices) {
        int[] best_days_to_buy_for = new int[prices.length + 1];
        best_days_to_buy_for[1] = 1;
        int best_sell_day = 1;
        int best_profit = Integer.MIN_VALUE;
        int profit = 0;
        for (int day = 2; day <= prices.length; day++) {
            if (prices[day - 1] < prices[best_days_to_buy_for[day - 1] - 1]) {
                best_days_to_buy_for[day] = day;
            } else {
                best_days_to_buy_for[day] = best_days_to_buy_for[day - 1];
            }
            profit = prices[day - 1] - prices[best_days_to_buy_for[day] - 1];
            if (profit > best_profit) {
                best_profit = profit;
                best_sell_day = day;
            }
        }
        return new int[] {best_days_to_buy_for[best_sell_day], best_sell_day};
    }

    // Kadane's algorithm
    // Here we just remember the current best buy day. We don't have an array of all best buy days.
    // Time complexity O(n)
    // Space complexity O(1)
    public static int[] bestDayToBuyAndSellKA(int[] prices) {
        int best_profit = Integer.MIN_VALUE;
        int profit;
        int best_buy_day = 1;
        int best_sell_day = 1;
        int buy_day = 1;
        for (int day = 2; day <= prices.length; day++) {
            if (prices[day - 1] < prices[buy_day - 1]) {
                buy_day = day;
            }
            profit = prices[day - 1] - prices[buy_day - 1];
            if (profit > best_profit) {
                best_profit = profit;
                best_sell_day = day;
                best_buy_day = buy_day;
            }
        }
        return new int[]{best_buy_day, best_sell_day};
    }
}
