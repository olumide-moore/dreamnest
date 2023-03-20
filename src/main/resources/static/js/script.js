//JavaScript for Header:
const bar = document.getElementById('bar');
const close = document.getElementById('close');
const nav = document.getElementById('navbar');

if (bar) {
    bar.addEventListener('click', () => {
      nav.classList.add('active');
    })
}

if (close) {
    close.addEventListener('click', () => {
      nav.classList.remove('active');
    })
}

//JavaScript for scrolling table:

const heads = ["text", "num", "short number" ];
const alpha = [...Array(26).keys()].map(i => String.fromCharCode(i+65));
const content = [...Array(500).keys()].map(i => ({
  text: [...Array(Math.round(Math.random()*50+20)).keys()].map(i => alpha[Math.round(Math.random()*26)]).join(""),
  num: Math.round(Math.random()*Math.pow(10,Math.round(Math.random()*10+5))),
  shortNum: Math.round(Math.random()*2000)/10
}));

d3.select("#tabhead").select('thead')
  .append('tr')
  .selectAll('th')
    .data(heads).enter().append('th')
      .text(n => n);
d3.select("#tabfull").select('thead')
  .append('tr')
  .selectAll('th')
    .data(heads).enter().append('th')
      .text(n => n);
d3.select("#tabfull").select('tbody')
  .selectAll('tr')
    .data(content).enter().append('tr')
      .each((n, i, nd) => {
        d3.select(nd[i]).selectAll('td')
          .data(Object.keys(n)).enter().append('td')
            .text(k => n[k])
      });

const reArrange = () => { 
  d3.select('#tabfull')
    .style('margin-top', (-1 * d3.select('#tabscroll').select('thead').node().getBoundingClientRect().height) + 'px')
    .select('thead')
      .style('visibility', 'hidden');
  let widths=[]; 
  d3.select('#tabfull').select('thead').selectAll('th')
    .each((n, i, nd) => widths.push(nd[i].clientWidth));
  d3.select('#tabhead').select('thead').selectAll('th')
    .each((n, i, nd) => d3.select(nd[i])
          .style('padding-right', 0)
          .style('padding-left', 0)
          .style('width', widths[i]+'px'));
};
let oldOnResize = window.onresize;
window.onresize = function() { 
  reArrange();
  if(oldOnResize) {
    return oldOnResize.apply(window, arguments);
  }
};
setTimeout(reArrange);