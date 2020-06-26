if ( ! Detector.webgl ) Detector.addGetWebGLMessage();

var camera, scene, renderer;

function init(obj3D, div, ctr) {

  // Initialize div
  div.innerHTML = "";

  // Setup params
  var divW = div.offsetWidth;
  var divH = 600;
  var divRatio = div.offsetWidth / divH;
  var fov = 45;

  // Scene Init
  scene = new THREE.Scene();
  scene.add( new THREE.AmbientLight( 0x999999 ) );

  // Grid Setup
  var grid = new THREE.GridHelper( 20, 20, 0xffffff, 0x555555 );
  grid.rotateOnAxis( new THREE.Vector3( 1, 0, 0 ), 90 * ( Math.PI/180 ) );
  scene.add( grid );

  // Camera Setup
  camera = new THREE.PerspectiveCamera( fov, divRatio, 1, 1000 );
  camera.up.set( 0, 0, 1 );
  camera.position.set( 0, -9, 6 );
  camera.lookAt( grid );
  pointlight = new THREE.PointLight( 0xffffff, 2, 0 )
  pointlight.castShadow = true;
  camera.add( pointlight );
  scene.add( camera );

  // Renderer Setup
  renderer = new THREE.WebGLRenderer( { antialias: true } );
  renderer.setClearColor( 0xffffff );
  renderer.setPixelRatio( window.devicePixelRatio );
  renderer.setSize( divW, divH );
  div.appendChild( renderer.domElement );

  // Loader Setup
  var usewireframe = false
  if ( ctr[0].checked ) {
    usewireframe = true
  }
  var loader = new THREE.STLLoader();
  var material = new THREE.MeshPhongMaterial( { color: 0x0e2045, specular: 0x111111, shininess: 200 , wireframe: usewireframe} );
  loader.load( obj3D, function ( geometry ) {
    var mesh = new THREE.Mesh( geometry, material );
    mesh.position.set( 0, 0, 0 );
    mesh.rotation.set( 0, 0, 0 );
    mesh.scale.set( .02, .02, .02 );
    mesh.castShadow = true;
    mesh.receiveShadow = true;
    scene.add( mesh );
    render();
  });

  // Add controller
  var controls = new THREE.OrbitControls( camera, renderer.domElement );
  controls.addEventListener( 'change', render );
  controls.target.set( 0, 1.2, 2 );
  controls.update();
  window.addEventListener( 'resize', onWindowResize(divW, divH), false );

}

function onWindowResize(divW, divH) {
  return function () {
    camera.aspect = divW / divH;
    camera.updateProjectionMatrix();
    renderer.setSize( divW, divH );
  }
}

function render() {
  renderer.render( scene, camera );
}