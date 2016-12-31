library(readr)
#import all trial runs
random <- read_csv("~/Documents/workspace/2048/RawData/random.txt", col_names = FALSE)
names(random) <- c("score", "highTile", "moves")

depth50 <- read_csv("~/Documents/workspace/2048/RawData/depth50.txt", col_names = FALSE)
names(depth50) <- c("score", "highTile", "moves")

depth100 <- read_csv("~/Documents/workspace/2048/RawData/depth100.txt", col_names = FALSE)
names(depth100) <- c("score", "highTile", "moves")

depth500 <- read_csv("~/Documents/workspace/2048/RawData/depth500.txt", col_names = FALSE)
names(depth500) <- c("score", "highTile", "moves")

depth1000 <- read_csv("~/Documents/workspace/2048/RawData/depth1000.txt", col_names = FALSE)
names(depth1000) <- c("score", "highTile", "moves")

depth10000 <- read_csv("~/Documents/workspace/2048/RawData/depth10000.txt", col_names = FALSE)
names(depth10000) <- c("score", "highTile", "moves")

depth10000 <- read_csv("~/Documents/workspace/2048/RawData/depth10000.txt", col_names = FALSE)
names(depth10000) <- c("score", "highTile", "moves")

#import all data runs
depth50allmoves <- read_csv("~/Documents/workspace/2048/RawData/depth50allmoves.txt", col_names = FALSE)
names(depth50allmoves) <- c("trailType", "moveNum", "score", "highTile", "moves")

depth100allmoves <- read_csv("~/Documents/workspace/2048/RawData/depth100allmoves.txt", col_names = FALSE)
names(depth100allmoves) <- c("trailType", "moveNum", "score", "highTile", "moves")

depth500allmoves <- read_csv("~/Documents/workspace/2048/RawData/depth500allmoves.txt", col_names = FALSE)
names(depth500allmoves) <- c("trailType", "moveNum", "score", "highTile", "moves")

depth1000allmoves <- read_csv("~/Documents/workspace/2048/RawData/depth1000allmoves.txt", col_names = FALSE)
names(depth1000allmoves) <- c("trailType", "moveNum", "score", "highTile", "moves")

depth10000allmoves <- read_csv("~/Documents/workspace/2048/RawData/depth10000allmoves.txt", col_names = FALSE)
names(depth10000allmoves) <- c("trailType", "moveNum", "score", "highTile", "moves")



#graphing
par(lty = 1.5, las=1)
options(scipen=999)

#save plot
png(file="/Users/Admin/Documents/workspace/2048/Graphs/highTileForAll.png")

#highTile for all
a = ts(depth50$highTile)
b = ts(depth100$highTile)
c = ts(depth500$highTile)
d =  ts(depth1000$highTile)
e = ts(random$score)
ts.plot(a, b, c, d, e, gpars = list(main="Highest Tile Across Depths", ylab="Highest Tile", xlab="Trial #",yaxt="n",col = c("black", "red", "green", "blue", "purple")))
legend("topright", title="Depths", legend = c("50","100","500","1000", "random"), col = 1:4, lty = 1)
axis(1)
axis(2, at=c(-40,240,552,1024,2048,4096,8192), labels=c(128,256,512,1024,2048,4096,8192))

dev.off()


#save plot
png(file="/Users/Admin/Documents/workspace/2048/Graphs/highScoreForAll.png")

#highScore for all
a = ts(depth50$score)
b = ts(depth100$score)
c = ts(depth500$score)
d =  ts(depth1000$score)
e = ts(random$score)
ts.plot(a, b, c, d, e, gpars = list(main="Highest Score Across Depths", ylab="Final Score", xlab="Trial #", col = c("black", "red", "green", "blue", "purple")))
legend("topright", title="Depths", legend = c("50","100","500","1000", "random"), col = 1:4, lty = 1)

dev.off()

#save plot
png(file="/Users/Admin/Documents/workspace/2048/Graphs/totalMovesFor50.png")

#total moves for all
a = ts(depth50$moves)
b = ts(depth100$moves)
c = ts(depth500$moves)
d =  ts(depth1000$moves)
e = ts(random$moves)
ts.plot(a, b, c, d, e, gpars = list(main="Game Length Across Depths", ylab="Game length (in moves)", xlab="Trial #", col = c("black", "red", "green", "blue", "purple")))
legend("topright", title="Depths", legend = c("50","100","500","1000", "random"), col = 1:4, lty = 1)

dev.off()

#save plot
png(file="/Users/Admin/Documents/workspace/2048/Graphs/totalMovesFor50.png")

#total moves for 50
a = ts(depth50$moves)
e = ts(random$moves)
ts.plot(a, e, gpars = list(main="Total Moves for Depth 50", ylab="Game length (in moves)", xlab="Trial #", col = c("black", "purple")))
legend("topright", title="Depths", legend = c("50","random"), col = c("black", "purple"), lty = 1)
abline(col = "black", h=mean(depth50$moves))
text(4, mean(depth50$moves)+30, "Mean", col = "black", cex=.8)

dev.off()


#save plot
png(file="/Users/Admin/Documents/workspace/2048/Graphs/totalMovesFor100.png")

#total moves for 100
a = ts(depth100$moves)
e = ts(random$moves)
ts.plot(a, e, gpars = list(main="Total Moves for Depth 100", ylab="Game length (in moves)", xlab="Trial #", col = c("red", "purple")))
legend("topright", title="Depths", legend = c("100","random"), col = c("red", "purple"), lty = 1)
abline(col = "red", h=mean(depth100$moves))
text(4, mean(depth100$moves)+30, "Mean", col = "black", cex=.8)

dev.off()


#save plot
png(file="/Users/Admin/Documents/workspace/2048/Graphs/totalMovesFor500.png")

#total moves for 500
a = ts(depth500$moves)
e = ts(random$moves)
ts.plot(a, e, gpars = list(main="Total Moves for Depth 500", ylab="Game length (in moves)", xlab="Trial #", col = c("green", "purple")))
legend("topright", title="Depths", legend = c("500","random"), col = c("green", "purple"), lty = 1)
abline(col = "green", h=mean(depth500$moves))
text(4, mean(depth500$moves)+30, "Mean", col = "black", cex=.8)

dev.off()


#save plot
png(file="/Users/Admin/Documents/workspace/2048/Graphs/totalMovesFor1000.png")

#total moves for 1000
a = ts(depth1000$moves)
e = ts(random$moves)
ts.plot(a, e, gpars = list(main="Total Moves for Depth 1000" ,ylab="Game length (in moves)", xlab="Trial #", col = c("blue", "purple")))
legend("topright", title="Depths", legend = c("1000","random"), col = c("blue", "purple"), lty = 1)
abline(col = "blue", h=mean(depth1000$moves))
text(4, mean(depth1000$moves)+30, "Mean", col = "black", cex=.8)

dev.off()


#save plot
png(file="/Users/Admin/Documents/workspace/2048/Graphs/totalScoreFor50.png")

#total score for 50
a = ts(depth50$score)
e = ts(random$score)
ts.plot(a, e, gpars = list(main="End Score for Depth 50", ylab="Score", xlab="Trial #", col = c("black", "purple")))
legend("topright", title="Depths", legend = c("50","random"), col = c("black", "purple"), lty = 1)
abline(col = "black", h=mean(depth50$score))
text(4, mean(depth50$score)+30, "Mean", col = "black", cex=.8)

dev.off()


#save plot
png(file="/Users/Admin/Documents/workspace/2048/Graphs/totalScoreFor100.png")

#total score for 100
a = ts(depth100$score)
e = ts(random$score)
ts.plot(a, e, gpars = list(main="End Score for Depth 100", ylab="Score", xlab="Trial #", col = c("red", "purple")))
legend("topright", title="Depths", legend = c("100","random"), col = c("red", "purple"), lty = 1)
abline(col = "red", h=mean(depth100$score))
text(4, mean(depth100$score)+30, "Mean", col = "black", cex=.8)

dev.off()

#save plot
png(file="/Users/Admin/Documents/workspace/2048/Graphs/totalScoreFor500.png")

#total score for 500
a = ts(depth500$score)
e = ts(random$score)
ts.plot(a, e, gpars = list(main="End Score for Depth 500", ylab="Score", xlab="Trial #", col = c("green", "purple")))
legend("topright", title="Depths", legend = c("500","random"), col = c("green", "purple"), lty = 1)
abline(col = "green", h=mean(depth500$score))
text(4, mean(depth500$score)+30, "Mean", col = "black", cex=.8)


dev.off()


#save plot
png(file="/Users/Admin/Documents/workspace/2048/Graphs/totalScoreFor1000.png")

#total score for 1000
a = ts(depth1000$score)
e = ts(random$score)
ts.plot(a, e, gpars = list(main="End Score for Depth 1000", ylab="Score", xlab="Trial #", col = c("blue", "purple")))
legend("topright", title="Depths", legend = c("1000","random"), col = c("blue", "purple"), lty = 1)
abline(col = "blue", h=mean(depth1000$score))
text(4, mean(depth1000$score)+30, "Mean", col = "black", cex=.8)


dev.off()


#save plot
png(file="/Users/Admin/Documents/workspace/2048/Graphs/highTileFor50.png")

#highestTile for 50
a = ts(depth50$highTile)
e = ts(random$highTile)
ts.plot(a, e, gpars = list(main="Highest Tile for Depth 50", ylab="Highest Tile", xlab="Trial #", col = c("black", "purple")))
legend("topright", title="Depths", legend = c("50","random"), col = c("black", "purple"), lty = 1)

dev.off()


#save plot
png(file="/Users/Admin/Documents/workspace/2048/Graphs/highTileFor100.png")

#highestTile for 100
a = ts(depth100$highTile)
e = ts(random$highTile)
ts.plot(a, e, gpars = list(main="Highest Tile for Depth 100", ylab="Highest Tile", xlab="Trial #", col = c("red", "purple")))
legend("topright", title="Depths", legend = c("100","random"), col = c("red", "purple"), lty = 1)

dev.off()



#save plot
png(file="/Users/Admin/Documents/workspace/2048/Graphs/highTileFor500.png")

#highestTile for 500
a = ts(depth500$highTile)
e = ts(random$highTile)
ts.plot(a, e, gpars = list(main="Highest Tile for Depth 500",ylab="Highest Tile", xlab="Trial #", col = c("green", "purple")))
legend("topright", title="Depths", legend = c("500","random"), col = c("green", "purple"), lty = 1)

dev.off()


#save plot
png(file="/Users/Admin/Documents/workspace/2048/Graphs/highTileFor1000.png")

#highestTile for 1000
a = ts(depth1000$highTile)
e = ts(random$highTile)
ts.plot(a, e, gpars = list(main="Highest Tile for Depth 1000",ylab="Highest Tile", xlab="Trial #", col = c("blue", "purple")))
legend("topright", title="Depths", legend = c("1000","random"), col = c("blue", "purple"), lty = 1)

dev.off()


#save plot
png(file="/Users/Admin/Documents/workspace/2048/Graphs/scoreRangePerMovedepth1000.png")

#Score Range Per Move\nDepth 1000"
a = ts(depth1000allmoves$score)
ts.plot(a, gpars=list(xaxt="n"),main="Score Range Per Move\nDepth 1000e", ylab="Predicted Final Score", xlab="Percent to Completion")
axis(2)
axis(1, at=seq(0, length(depth1000allmoves$score), by = length(depth1000allmoves$score)/100), labels=seq(0, 100, by=1))

dev.off()


#save plot
png(file="/Users/Admin/Documents/workspace/2048/Graphs/moveRangePerMovedepth1000.png")

#Move Range Per Move\nDepth 1000"
a = ts(depth1000allmoves$moves)
ts.plot(a, gpars=list(xaxt="n"),main="Move Range Per Move\nDepth 1000", ylab="Predicted Move Count Until Completion", xlab="Percent to Completion")
axis(2)
axis(1, at=seq(0, length(depth1000allmoves$score), by = length(depth1000allmoves$score)/100), labels=seq(0, 100, by=1))

dev.off()


#save plot
png(file="/Users/Admin/Documents/workspace/2048/Graphs/highTilePerMovedepth1000.png")

#High Tile Range Per Move\nDepth 1000"
a = ts(depth1000allmoves$highTile)
ts.plot(a, gpars=list(xaxt="n"),main="High Tile Range Per Move\nDepth 1000", ylab="Predicted High Tile", xlab="Percent to Completion")
axis(2)
axis(1, at=seq(0, length(depth1000allmoves$score), by = length(depth1000allmoves$score)/100), labels=seq(0, 100, by=1))

dev.off()

