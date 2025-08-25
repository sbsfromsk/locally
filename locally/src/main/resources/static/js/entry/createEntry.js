const uploadArea = document.getElementById('uploadArea');
const previewArea = document.getElementById('previewArea');
const imageBlock = document.getElementById('imageBlock');
const fileInput = document.getElementById('fileInput');
const entryDate = document.querySelector("#entryDate");


uploadArea.addEventListener('click', function(event) {
	console.log(event.target);
	document.getElementById('fileInput').click();
});

fileInput.addEventListener('change', function(event) {
	console.log("바뀌었슴..");
	console.log(event);
	console.log(event.target);
	console.log(event.target.result);
	console.log(event.target.files[0]); // file에 대한 정보 얻기!

	imageBlock.innerHTML = '';

	for (let imageFiles of event.target.files) {
		let img = document.createElement('img');
		img.src = URL.createObjectURL(imageFiles);
		img.classList.add('preview-img');
		img.draggable = false;
		imageBlock.appendChild(img);
	}

	/*
	const file = event.target.files[0];
	console.log("이미지 미리보기 시작");
	console.log(URL.createObjectURL(file));
	//previewArea.innerHTML = '';
	imageBlock.innerHTML = '';
	
	const img = document.createElement('img');
	img.src = URL.createObjectURL(file);
	img.classList.add('preview-img');
	console.log("뛰뛰:" + imageBlock);
	//img.style.width = '150px';
	//img.style.margin = '10px';
	
	imageBlock.appendChild(img);
	*/
	uploadArea.style.display = 'none';
	previewArea.style.display = 'flex';

	console.log("imageBlock.innerHTML:", imageBlock.innerHTML);

});

const slider = document.getElementById("imageBlock");
let isDown = false;
let startX;
let scrollLeft;

slider.addEventListener('mousedown', e => {
	isDown = true;
	slider.classList.add('active');
	startX = e.pageX - slider.offsetLeft;
	scrollLeft = slider.scrollLeft;
});

slider.addEventListener('mouseleave', () => {
	isDown = false;
	slider.classList.remove('active');
});

slider.addEventListener('mouseup', () => {
	isDown = false;
	slider.classList.remove('active');
});

slider.addEventListener('mousemove', e => {
	if(!isDown) return;
	
	
	e.preventDefault();
	const x = e.pageX - slider.offsetLeft;
	const walk = x - startX;
	console.log("x" + x);
	slider.scrollLeft = scrollLeft - walk;
	});
	
entryDate.addEventListener('click', e => {
	const selectedDate = entryDate.value;
	console.log(selectedDate);
})