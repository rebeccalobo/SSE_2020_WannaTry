<%--

  User: kiowa
  Date: 09/02/2020
  Time: 21:41
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href='<f:url value ="/resources/css/Home.css?ver=<?php echo rand(111,999)?>"/>' type="text/css"/>
    <style>

    </style>

    <title>Wanna Try</title>
</head>
<body>
<script src="https://d3js.org/d3.v5.min.js" charset="utf-8"></script>



    <%@include file="sidebar.jsp"%><br>

    <div class="main">
<%--        Home page things go in here!--%>
        <div class="parallax_top"></div>
        <h1>Welcome to WannaTry University Portal</h1>
        <p> Here At Universtiy College Dublin we strive to be diverse and inclusive.
            Our vision is to create safe and effective learning spaces for students of all backgrounds!
        </p>
        <div class="parallax_gender"></div>
        <div id="pie-chart-area" style="text-align: center"></div>
        <p> Our modules are tailored to group work to ensure each student is given the oppertunity to broaded their minds
            through multi-cultural teamwork.
        </p>
        <div class="parallax_gender_class"></div>
        <p>
            Here at UCD we boast our impressive Employability Ranking - Number 1 in Ireland and a staggering 78 worldwide!
        </p>
        <div class="parallax_graduates"></div>


    </div>

</body>
</html>
<script>
    // set the dimensions and margins of the graph
    var width = 450
    height = 450
    margin = 40

    // The radius of the pieplot is half the width or half the height (smallest one). I subtract a bit of margin.
    var radius = Math.min(width, height) / 2 - margin

    // append the svg object to the div called 'my_dataviz'
    var svg = d3.select("#pie-chart-area")
        .append("svg")
        .attr("width", width)
        .attr("height", height)
        .append("g")
        .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");

    // Create dummy data

    var data = {Male:${genders.get("MALE")},Female:${genders.get("FEMALE")},Other:${genders.get("OTHER")}};

    // set the color scale
    var color = d3.scaleOrdinal()
        .domain(data)
        .range(["#10c500", "#0044ff", "#ffaf00", "#ff0200", "#a05d56"])

    // Compute the position of each group on the pie:
    var pie = d3.pie()
        .value(function(d) {return d.value; })
    var data_ready = pie(d3.entries(data))

    // shape helper to build arcs:
    var arcGenerator = d3.arc()
        .innerRadius(0)
        .outerRadius(radius)

    // Build the pie chart: Basically, each part of the pie is a path that we build using the arc function.
    svg
        .selectAll('whatever')
        .data(data_ready)
        .enter()
        .append('path')
        .attr('d', d3.arc()
            .innerRadius(0)
            .outerRadius(radius)
        )
        .attr('fill', function(d){ return(color(d.data.key)) })
        .attr("stroke", "black")
        .style("stroke-width", "2px")
        .style("opacity", 0.7)
    svg
        .selectAll('mySlices')
        .data(data_ready)
        .enter()
        .append('text')
        .text(function(d){ return d.data.key})
        .attr("transform", function(d) { return "translate(" + arcGenerator.centroid(d) + ")";  })
        .style("text-anchor", "middle")
        .style("font-size", 17)

</script>
